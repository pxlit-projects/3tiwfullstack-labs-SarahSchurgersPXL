package be.pxl.services.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.JavaBean;
@Builder
@JavaBean
public class Employee {


    private Long id;

    private Long organizationId;
    private Long departmentId;
    private String name;
    private int age;
    private String position;
}
