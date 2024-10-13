package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.dto.DepartmentDto;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findDepartmentById(long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department getDepartmentByOrganizationId(long organizationId) {
        return departmentRepository.findDepartmentByOrganizationId(organizationId);
    }

    @Override
    public Object getDepartmentAndEmployees(long organizationId) {
        return departmentRepository.findByOrganizationWithEmployees(organizationId);
    }

    @Override
    public void addDepartment(DepartmentDto dto) {
        createDepartment(dto);
    }

    public void createDepartment(DepartmentDto dto) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setOrganizationId(dto.getOrganizationId());
        department.setPosition(dto.getPosition());
        department.setEmployees(dto.getEmployees());
        departmentRepository.save(department);
    }
}
