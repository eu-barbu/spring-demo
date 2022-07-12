package com.demo.springdemo.dto;

import com.demo.springdemo.model.Department;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private int id;
    private String firstname;
    private String lastname;
    private int monthlySalary;
    private String departmentName;
}
