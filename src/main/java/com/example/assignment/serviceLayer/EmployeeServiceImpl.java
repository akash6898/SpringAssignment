package com.example.assignment.serviceLayer;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Override
    public String getEmployeeData() {
        return "dummy data";
    }
}
