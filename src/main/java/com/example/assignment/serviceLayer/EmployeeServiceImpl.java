package com.example.assignment.serviceLayer;

import com.example.assignment.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    Employee e;

    @Override
    public Employee getEmployeeData() {
        e.setId(1);
        e.setFirstName("Akash");
        e.setLastName("Soni");
        return e;
    }
}
