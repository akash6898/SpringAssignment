package com.example.assignment.controller;

import com.example.assignment.entities.Employee;
import com.example.assignment.serviceLayer.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public Employee getData()
    {
        return employeeService.getEmployeeData();
    }

}
