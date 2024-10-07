package be.pxl.services.services;

import be.pxl.services.domain.Organization;

import java.util.Optional;

public interface IOrganizationService {
    Optional<Organization> findOrganizationById(long id);

    Object getOrganizationWithDepartments(long id);

    Object getOrganizationWithDepartmentsAndEmployees(long id);

    Object getOrganizationWithEmployees(long id);
}
