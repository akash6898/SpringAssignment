package com.example.assignment.serviceLayer;

import com.example.assignment.daoLayer.EmployeeDaoLayer;
import com.example.assignment.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeDaoLayer employeeDaoLayer;

    @Autowired
    Employee e;

    @Override
    public List<Employee> getEmployeeData() {
        return employeeDaoLayer.findAll();
    }

    @Override
    public void addEmployee(Employee e) {
        employeeDaoLayer.save(e);
    }
}
