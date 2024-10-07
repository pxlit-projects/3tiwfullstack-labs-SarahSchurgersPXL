package be.pxl.services.controller;

import be.pxl.services.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final IOrganizationService organizationService;

    @GetMapping("/{id}")
    public ResponseEntity getOrganization(@PathVariable long id) {
        return new ResponseEntity(organizationService.findOrganizationById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/with-departments")
    public ResponseEntity getOrganizationWithDepartments(@PathVariable long id) {
        return new ResponseEntity(organizationService.getOrganizationWithDepartments(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/with-departments-and-employees")
    public ResponseEntity getOrganizationWithDepartmentsAndEmployees(@PathVariable long id) {
        return new ResponseEntity(organizationService.getOrganizationWithDepartmentsAndEmployees(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/with-employees")
    public ResponseEntity getOrganizationWithEmployees(@PathVariable long id) {
        return new ResponseEntity(organizationService.getOrganizationWithEmployees(id), HttpStatus.OK);
    }
}
