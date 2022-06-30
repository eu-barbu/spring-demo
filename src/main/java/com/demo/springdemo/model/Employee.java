package com.demo.springdemo.model;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
public class Employee {

    private String name;
    private int salary;
    private String department;
    private boolean active;
    private int age;
    private String location;

}
