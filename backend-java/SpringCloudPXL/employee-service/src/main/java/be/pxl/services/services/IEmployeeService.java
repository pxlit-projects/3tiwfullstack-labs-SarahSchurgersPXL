package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.dto.CreateEmployeeDto;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {


    List<Employee> getAllEmployees();

    Optional<Employee> findEmployeeById(Long id);

    List<Employee> getEmployeesByDepartmentId(long departmentId);

    List<Employee> getAllEmployeesByOrganizationId(long organizationId);

    void addEmployee(CreateEmployeeDto dto);
}
