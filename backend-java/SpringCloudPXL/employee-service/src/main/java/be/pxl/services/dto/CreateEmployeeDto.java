package be.pxl.services.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.pxl.services.domain.Employee}
 */
@Value
public class CreateEmployeeDto implements Serializable {
    Long organizationId;
    Long departmentId;
    String name;
    int age;
    String position;
}