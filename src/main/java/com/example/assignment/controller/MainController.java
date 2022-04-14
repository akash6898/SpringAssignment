package com.example.assignment.controller;

import com.example.assignment.entities.Employee;
import com.example.assignment.serviceLayer.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getData()
    {
        return employeeService.getEmployeeData();
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody Employee employee)
    {
        employeeService.addEmployee(employee);
       return new ResponseEntity(HttpStatus.OK);
    }

}
