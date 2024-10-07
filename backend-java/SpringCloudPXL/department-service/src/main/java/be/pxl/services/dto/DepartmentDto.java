package be.pxl.services.dto;

import be.pxl.services.domain.Employee;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link be.pxl.services.domain.Department}
 */
@Value
public class DepartmentDto implements Serializable {
    Long organizationId;
    String name;
    List<Employee> employees;
    String position;
}