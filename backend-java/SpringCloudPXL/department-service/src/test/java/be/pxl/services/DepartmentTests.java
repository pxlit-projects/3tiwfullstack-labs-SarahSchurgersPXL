package be.pxl.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import be.pxl.services.repository.DepartmentRepository;
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
public class DepartmentTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

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
    public void testCreateDepartment() throws Exception{
        Department department = Department.builder()
                .name("IT")
                .organizationId(123415L)
                .position("position")
                .build();

        String departmentString = objectMapper.writeValueAsString(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentString))
                .andExpect(status().isCreated());

        assertEquals(1,departmentRepository.findAll().size());
    }

    @Test
    public void testGetDepartments() throws Exception{
        Department department = Department.builder()
                .name("IT")
                .organizationId(123415L)
                .position("position")
                .build();

        departmentRepository.save(department);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/department"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(1, (int) JsonPath.read(response,"$.length()"));

    }

    @Test
    public void testGetDepartment() throws Exception{
        Department department = Department.builder()
                .name("IT")
                .Id(1L)
                .position("position")
                .build();

        departmentRepository.save(department);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/department/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("IT", JsonPath.read(response,"$.name"));
    }

    @Test
    public void testGetDepartmentsByOrganization() throws Exception{
        Department department = Department.builder()
                .name("IT")
                .organizationId(123415L)
                .position("position")
                .build();

        departmentRepository.save(department);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/123415"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(1, (int) JsonPath.read(response,"$.length()"));
    }

    @Test
    public void testGetDepartmentAndEmployees() throws Exception{
        Department department = Department.builder()
                .name("IT")
                .Id(1L)
                .organizationId(123415L)
                .position("position")
                .build();

        departmentRepository.save(department);

        Employee employee = Employee.builder()
                .age(24)
                .name("Jan")
                .position("student")
                .departmentId(1L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated());

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/123415/with-employees"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(1, (int) JsonPath.read(response,"$.length()"));
    }


}
