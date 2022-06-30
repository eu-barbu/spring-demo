package com.demo.springdemo.controller;

import com.demo.springdemo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IndexController {

    @GetMapping(value = "/index")
    public String index(Model model){
        Employee myEmployee = Employee.builder()
                .age(24)
                .location("Galati")
                .name("Max")
                .active(true)
                .salary(1600)
                .department("Logists")
                .build();

        System.out.println(myEmployee.toString());

        String text = "Hello from Spring Controller";

        model.addAttribute("greetings", text);
        model.addAttribute("myText", "some random text");

        List<String> fruits =List.of("mere", "banane", "pepeni", "pere", "cirese", "capsuni", "persici");
        model.addAttribute("fruits", fruits);

        List<Employee> employeeList = List.of(
               /* new Employee("Max", 6500, "IT Support"),
                new Employee("George", 8650, "Management"),
                new Employee("Andreea", 7345, "Logistics")*/
        );
        model.addAttribute("employeeList", employeeList);

        return "index";
    }

    @GetMapping(value = "/employeeForm")
    public String employeeForm(Model model){
        Employee myEmployee = Employee.builder().build();
        myEmployee.setName("Heloo");
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
        //TODO save to database
    }
}
