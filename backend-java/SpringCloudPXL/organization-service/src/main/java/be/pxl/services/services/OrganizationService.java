package be.pxl.services.services;

import be.pxl.services.domain.Organization;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public Optional<Organization> findOrganizationById(long id) {
        return organizationRepository.findById(id);
    }

    @Override
    public Object getOrganizationWithDepartments(long id) {
        return null;
    }

    @Override
    public Object getOrganizationWithDepartmentsAndEmployees(long id) {
        return null;
    }

    @Override
    public Object getOrganizationWithEmployees(long id) {
        return null;
    }
}
