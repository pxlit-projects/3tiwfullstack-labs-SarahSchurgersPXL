package be.pxl.services.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.JavaBean;
import java.util.List;
@Builder
@JavaBean
public class Department {


    private Long Id;
    private Long organizationId;
    private String name;
    private List<Employee> employees;
    private String position;
}
