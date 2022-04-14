package com.example.assignment.daoLayer;

import com.example.assignment.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDaoLayer extends JpaRepository<Employee, Integer> {
}
