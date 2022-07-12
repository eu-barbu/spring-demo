package com.demo.springdemo.service;

import com.demo.springdemo.dto.EmployeeDto;
import com.demo.springdemo.model.Employee;
import com.demo.springdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        /*List<EmployeeDto> employeeDtoList = employeeList.stream()
                .map(this::mapToEmployee)
                .collect(Collectors.toList());*/

        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee : employeeList){
            EmployeeDto employeeDto = EmployeeDto.builder()
                    .id(employee.getId())
                    .firstname(employee.getFirstname())
                    .lastname(employee.getLastname())
                    .monthlySalary(employee.getMonthlySalary())
                    .departmentName(employee.getDepartment().getCode() + " - " + employee.getDepartment().getName())
                    .build();
            employeeDtoList.add(employeeDto);
        }

        return employeeDtoList;
    }

    private EmployeeDto mapToEmployee(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .monthlySalary(employee.getMonthlySalary())
                .departmentName(employee.getDepartment().getCode() + " - " + employee.getDepartment().getName())
                .build();
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
