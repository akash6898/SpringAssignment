package com.example.assignment.serviceLayer;

import com.example.assignment.entities.Employee;

public interface EmployeeService {
    public Employee getEmployeeData();
    public void addEmployee(Employee employee);
}
