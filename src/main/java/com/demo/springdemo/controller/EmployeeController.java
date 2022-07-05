package com.demo.springdemo.controller;

import com.demo.springdemo.model.Employee;
import com.demo.springdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = "/createEmployee")
    @ResponseBody
    public String createEmployee(){
        Employee employee = Employee.builder()
                .firstname("George")
                .lastname("Popescu")
                .department("Accounting")
                .monthlySalary(2400)
                .build();
        employeeRepository.save(employee);
        return "OK!";
    }

    @GetMapping(value = "/showEmployees")
    @ResponseBody
    public String showEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        for (Employee e : employeeList){
            System.out.println(e.toString());
        }
        return "OK!";
    }
}
