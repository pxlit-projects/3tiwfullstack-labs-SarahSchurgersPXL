package be.pxl.services.controller;

import be.pxl.services.dto.DepartmentDto;
import be.pxl.services.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity getDepartments() {
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDepartment(@PathVariable long id) {
        return new ResponseEntity(departmentService.findDepartmentById(id), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity getDepartmentByOrganization(@PathVariable long organizationId) {
        return new ResponseEntity(departmentService.getDepartmentByOrganizationId(organizationId), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public ResponseEntity getDepartmentAndEmployees(@PathVariable long organizationId) {
        return new ResponseEntity(departmentService.getDepartmentAndEmployees(organizationId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createDepartment(@RequestBody DepartmentDto dto) {
        departmentService.addDepartment(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
