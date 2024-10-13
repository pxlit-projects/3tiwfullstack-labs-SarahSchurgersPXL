package be.pxl.services.controller;

import be.pxl.services.dto.CreateEmployeeDto;
import be.pxl.services.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final IEmployeeService employeeService;



    @GetMapping
    public ResponseEntity getEmployees() {
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployee(@PathVariable long id) {
        return new ResponseEntity(employeeService.findEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity getEmployeesByDepartment(@PathVariable long departmentId) {
        return new ResponseEntity(employeeService.getEmployeesByDepartmentId(departmentId), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity getEmployeesByOrganization(@PathVariable long organizationId) {
        return new ResponseEntity(employeeService.getAllEmployeesByOrganizationId(organizationId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody CreateEmployeeDto dto) {
        employeeService.addEmployee(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
