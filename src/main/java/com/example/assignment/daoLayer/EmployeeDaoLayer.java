package com.example.assignment.daoLayer;

import com.example.assignment.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDaoLayer extends JpaRepository<Employee, Integer> {
}