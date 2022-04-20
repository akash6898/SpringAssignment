package com.example.assignment.serviceLayer;

import com.example.assignment.dtoLayer.AddressDto;
import com.example.assignment.dtoLayer.EmployeeDto;
import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;
import com.example.assignment.execption.CustomExecption;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployeeData();
    public Page<Employee> sortedEmployeeWithPagination(int pageNo,int pageSize);
    public Employee addEmployee(EmployeeDto employeeDto);
    public Employee searchByEmployeeId(int employeeId) throws InterruptedException, CustomExecption;
    public Employee editEmployee(EmployeeDto employeeDto) throws CustomExecption;
    public boolean deleteEmployee(int employeeId);
    public Employee addAddress(int employeeId, AddressDto addressDto) throws InterruptedException, CustomExecption;
    public Employee editAddress(int employeeId,AddressDto addressDto) throws InterruptedException, CustomExecption;
    public Employee deleteAddress(int addressId);
    public Employee deleteAllAddress(int employeeId) throws InterruptedException, CustomExecption;
    public List<Employee> searchByPinCode(int pinCode);
    public List<Employee> searchByCity(String city);
    public List<Employee> searchByState(String city);
    public List<Employee> searchByCountry(String country);
}



