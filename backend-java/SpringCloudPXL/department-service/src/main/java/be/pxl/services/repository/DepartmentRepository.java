package be.pxl.services.repository;

import be.pxl.services.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
  Department findDepartmentByOrganizationId(long organizationId);

  @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees WHERE d.organization.id = :organizationId")
  Department findByOrganizationWithEmployees(@Param("organizationId") long organizationId);


}