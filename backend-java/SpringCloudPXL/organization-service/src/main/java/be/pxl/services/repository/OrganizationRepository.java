package be.pxl.services.repository;

import be.pxl.services.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findById(long id);

    @Query("SELECT o FROM Organization o LEFT JOIN FETCH o.departments WHERE o.id = :id")
    Organization findByIdWithDepartments(@Param("id") long id);

    @Query("SELECT o FROM Organization o LEFT JOIN FETCH o.departments d LEFT JOIN FETCH d.employees WHERE o.id = :id")
    Organization findByIdWithDepartmentsAndEmployees(@Param("id") long id);

    @Query("SELECT o FROM Organization o LEFT JOIN FETCH o.employees WHERE o.id = :id")
    Organization findByIdWithEmployees(@Param("id") long id);


}