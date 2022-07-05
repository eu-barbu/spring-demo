package com.demo.springdemo.controller;

import com.demo.springdemo.model.Employee;
import com.demo.springdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = "/index")
    public String index(Model model){
        Employee myEmployee = Employee.builder()
                .firstname("Max")
                .monthlySalary(1600)
                .department("Logists")
                .build();

        System.out.println(myEmployee.toString());

        String text = "Hello from Spring Controller";

        model.addAttribute("greetings", text);
        model.addAttribute("myText", "some random text");

        List<String> fruits =List.of("mere", "banane", "pepeni", "pere", "cirese", "capsuni", "persici");
        model.addAttribute("fruits", fruits);

        List<Employee> employeeList = employeeRepository.findAll();
        model.addAttribute("employeeList", employeeList);

        return "index";
    }

    @GetMapping(value = "/employeeForm")
    public String employeeForm(Model model){
        Employee myEmployee = Employee.builder().build();
        model.addAttribute("employee", myEmployee);
        return "employeeForm";
    }

    @PostMapping(value = "/submitEmployee")
    public String submitEmployee(@ModelAttribute Employee employee){
        System.out.println(employee.toString());
        saveToDatbase(employee);
        return "index";
    }

    private void saveToDatbase(Employee employee) {
        employeeRepository.save(employee);
    }
}
