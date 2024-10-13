package be.pxl.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.Organization;
import be.pxl.services.repository.OrganizationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class OrganizationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Container
    private static MySQLContainer sqlContainer =
            new MySQLContainer("mysql:5.7.37");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username",sqlContainer::getUsername);
        registry.add("spring.datasource.password",sqlContainer::getPassword);
    }

    @Test
    public void testGetOrganization() throws Exception{
        Organization organization = Organization.builder()
                .name("PXL")
                .id(1L)
                .build();

        organizationRepository.save(organization);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("PXL", JsonPath.read(response,"$.name"));
    }

    @Test
    public void testGetOrganizationWithDepartments() throws Exception {
        Organization organization = Organization.builder()
                .name("PXL")
                .id(1L)
                .build();

        organizationRepository.save(organization);

        Department department = Department.builder()
                .name("ICT")
                .organizationId(organization.getId())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isCreated());

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/1/with-departments"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(1, (int) JsonPath.read(response,"$.length()"));
        assertEquals(1, (int) JsonPath.read(response,"$[0].departments.length()"));
    }

    @Test
    public void testGetOrganizationWithDepartmentsAndEmployees() throws Exception {
        Organization organization = Organization.builder()
                .name("PXL")
                .id(1L)
                .build();

        organizationRepository.save(organization);

        Department department = Department.builder()
                .name("ICT")
                .organizationId(organization.getId())
                .Id(2L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isCreated());

        Employee employee = Employee.builder()
                .name("John")
                .departmentId(2L)
                .organizationId(1L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated());

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/1/with-departments-and-employees"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(1, (int) JsonPath.read(response,"$.length()"));
        assertEquals(1, (int) JsonPath.read(response,"$[0].departments.length()"));
        assertEquals(1, (int) JsonPath.read(response,"$[0].departments[0].employees.length()"));
    }

    @Test
    public void testGetOrganizationWithEmployees() throws Exception {
        Organization organization = Organization.builder()
                .name("PXL")
                .id(1L)
                .build();

        organizationRepository.save(organization);


        Employee employee = Employee.builder()
                .name("John")
                .organizationId(1L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated());

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/1/with-employees"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(1, (int) JsonPath.read(response,"$.length()"));
        assertEquals(1, (int) JsonPath.read(response,"$[0].employees.length()"));
    }

}
