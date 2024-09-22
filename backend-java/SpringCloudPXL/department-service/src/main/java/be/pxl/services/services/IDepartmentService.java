package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.dto.DepartmentDto;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    List<Department> getAllDepartments();

    Optional<Department> findDepartmentById(long id);

    Department getDepartmentByOrganizationId(long organizationId);

    Object getDepartmentAndEmployees(long organizationId);

    void addDepartment(DepartmentDto dto);
}
