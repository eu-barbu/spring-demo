package com.demo.springdemo.controller;

import com.demo.springdemo.model.Department;
import com.demo.springdemo.model.Employee;
import com.demo.springdemo.repository.DepartmentRepository;
import com.demo.springdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping(value = "/employeeOverview")
    public String employeeOverview(Model model){
        List<Employee> employeeList = employeeRepository.findAll();
        model.addAttribute("employeeList", employeeList);
        return "employeeOverview";
    }

    @GetMapping(value = "/employeeForm")
    public String employeeForm(Model model){
        Employee myEmployee = Employee.builder().build();
        model.addAttribute("employee", myEmployee);

        List<Department> departmentList = departmentRepository.findAll();
        model.addAttribute("departmentList", departmentList);

        return "employeeForm";
    }

    @PostMapping(value = "/submitEmployee")
    public String submitEmployee(@ModelAttribute Employee employee, Model model){
        employeeRepository.save(employee);
        return "redirect:/employeeOverview";
    }

    @PostMapping(value = "/editEmployee")
    public String editEmployee(@RequestParam("employeeId") int id, Model model){
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);

        List<Department> departmentList = departmentRepository.findAll();
        model.addAttribute("departmentList", departmentList);

        return "employeeForm";
    }

    @PostMapping(value = "/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int id){
        employeeRepository.deleteById(id);
        return "redirect:/employeeOverview";
    }

}
