package com.demo.springdemo.controller;

import com.demo.springdemo.dto.EmployeeDto;
import com.demo.springdemo.model.Department;
import com.demo.springdemo.model.Employee;
import com.demo.springdemo.service.DepartmentService;
import com.demo.springdemo.service.EmployeeService;
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
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/employeeOverview")
    public String employeeOverview(Model model){
        List<EmployeeDto> employeeList = employeeService.getAllEmployees();
        model.addAttribute("employeeList", employeeList);
        return "employeeOverview";
    }

    @GetMapping(value = "/employeeForm")
    public String employeeForm(Model model){
        Employee myEmployee = Employee.builder().build();
        model.addAttribute("employee", myEmployee);

        List<Department> departmentList = departmentService.getAllDepartments();
        model.addAttribute("departmentList", departmentList);

        return "employeeForm";
    }

    @PostMapping(value = "/submitEmployee")
    public String submitEmployee(@ModelAttribute Employee employee, Model model){
        employeeService.saveEmployee(employee);
        return "redirect:/employeeOverview";
    }

    @PostMapping(value = "/editEmployee")
    public String editEmployee(@RequestParam("employeeId") int id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        List<Department> departmentList = departmentService.getAllDepartments();
        model.addAttribute("departmentList", departmentList);

        return "employeeForm";
    }

    @PostMapping(value = "/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int id){
        employeeService.deleteEmployee(id);
        return "redirect:/employeeOverview";
    }

}
