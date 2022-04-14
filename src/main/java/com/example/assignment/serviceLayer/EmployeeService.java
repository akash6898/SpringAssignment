package com.example.assignment.serviceLayer;

import com.example.assignment.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getEmployeeData();
    public void addEmployee(Employee employee);
}
