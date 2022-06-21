package com.demo.springdemo.controller;

import com.demo.springdemo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @GetMapping(value = "/index")
    public String index(Model model){

        String text = "Hello from Spring Controller";

        model.addAttribute("greetings", text);
        model.addAttribute("myText", "some random text");

        List<String> fruits =List.of("mere", "banane", "pepeni", "pere", "cirese", "capsuni", "persici");
        model.addAttribute("fruits", fruits);

        List<Employee> employeeList = List.of(
                new Employee("Max", 6500, "IT Support"),
                new Employee("George", 8650, "Management"),
                new Employee("Andreea", 7345, "Logistics")
        );
        model.addAttribute("employeeList", employeeList);

        return "index";
    }
}
