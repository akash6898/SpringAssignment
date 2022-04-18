package com.example.assignment.serviceLayer;

import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployeeData();
    public Employee addEmployee(Employee employee);
    public Employee searchByEmployeeId(int employeeId) throws InterruptedException;
    public Employee editEmployee(Employee employee);
    public void deleteEmployee(int employeeId);
    public Employee addAddress(int employeeId, Address address) throws InterruptedException;
    public Employee editAddress(int employeeId,Address address) throws InterruptedException;
    public Employee deleteAddress(int addressId);
    public Employee deleteAllAddress(int employeeId) throws InterruptedException;
    public List<Employee> searchByPinCode(int pinCode);
    public List<Employee> searchByCity(String city);
    public List<Employee> searchByState(String city);
    public List<Employee> searchByCountry(String country);
}



