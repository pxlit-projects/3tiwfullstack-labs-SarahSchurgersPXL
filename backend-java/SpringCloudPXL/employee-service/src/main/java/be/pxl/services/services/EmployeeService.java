package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.dto.CreateEmployeeDto;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(long departmentId) {
        return employeeRepository.getEmployeesByDepartmentId(departmentId);
    }

    @Override
    public List<Employee> getAllEmployeesByOrganizationId(long organizationId) {
        return employeeRepository.getEmployeesByOrganizationId(organizationId);
    }

    @Override
    public void addEmployee(CreateEmployeeDto dto) {
        createEmployee(dto);
    }

    public void createEmployee(CreateEmployeeDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartmentId(dto.getDepartmentId());
        employee.setOrganizationId(dto.getOrganizationId());
        employee.setAge(dto.getAge());
        employee.setPosition(dto.getPosition());
        employeeRepository.save(employee);
    }


}
