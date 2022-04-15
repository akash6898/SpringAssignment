package com.example.assignment.daoLayer;

import com.example.assignment.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDaoLayer extends JpaRepository<Employee, Integer> {

    List<Employee> findByaddresses_PinCode(String pinCode);
    List<Employee> findByaddresses_CityName(String city);
    List<Employee> findByaddresses_State(String state);
    List<Employee> findByaddresses_Country(String country);
}
