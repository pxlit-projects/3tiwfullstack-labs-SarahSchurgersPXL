package be.pxl.services.domain;


import lombok.Builder;


import java.beans.JavaBean;

@JavaBean
@Builder
public class Employee {


    private Long id;

    private Long organizationId;
    private Long departmentId;
    private String name;
    private int age;
    private String position;

}
