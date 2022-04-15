package com.example.assignment.serviceLayer;

import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployeeData();
    public void addEmployee(Employee employee);
    public void addBulkEmployee(List<Employee> employeeList);
    public Employee searchByEmployeeId(int employeeId);
    public void editEmployee(Employee employee);
    public void deleteEmployee(int employeeId);
    public void addAddress(int employeeId, Address address);
    public void editAddress(int employeeId,Address address);
    public void deleteAddress(int addressId);
    public void deleteAllAddress(int employeeId);
    public List<Employee> searchByPinCode(int pinCode);
    public List<Employee> searchByCity(String city);
    public List<Employee> searchByState(String city);
    public List<Employee> searchByCountry(String country);
}



