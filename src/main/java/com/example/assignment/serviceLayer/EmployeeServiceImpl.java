package com.example.assignment.serviceLayer;

import com.example.assignment.daoLayer.AddressDaoLayer;
import com.example.assignment.daoLayer.EmployeeDaoLayer;
import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeDaoLayer employeeDaoLayer;

    @Autowired
    AddressDaoLayer addressDaoLayer;


    @Override
    public List<Employee> getAllEmployeeData() {

        return employeeDaoLayer.findAll();
    }

    @Override
    public void addEmployee(Employee e) {
        System.out.println(e);
        employeeDaoLayer.save(e);
    }

    @Override
    public Employee searchByEmployeeId(int employeeId) {
        Optional<Employee> employee =  employeeDaoLayer.findById(employeeId);
        if(employee.isPresent())
        {
            return employee.get();
        }
        return  null;
    }

    @Override
    public void editEmployee(Employee employee) {
        employeeDaoLayer.save(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeDaoLayer.deleteById(employeeId);
    }

    @Override
    public void addAddress(int employeeId, Address address) {
        Employee employee = employeeDaoLayer.getById(employeeId);
        employee.addAddresses(address);
        employeeDaoLayer.save(employee);
    }

    @Override
    public void editAddress(int employeeId, Address address) {
        Employee employee = employeeDaoLayer.getById(employeeId);
        List<Address> addressList = employee.getAddresses();
        Stream<Address> addressStream = addressList.stream().map(address1 -> {

            if(address.getAddressId() == address1.getAddressId())
            {

                return address;
            }
            return address1;
        });

      employee.setAddresses(addressStream.collect(Collectors.toList()));
        System.out.println(employee.getAddresses());
        employeeDaoLayer.save(employee);
    }

    @Override
    public void deleteAddress(int addressId) {
        addressDaoLayer.deleteById(addressId);
    }

    @Override
    public void deleteAllAddress(int employeeId) {
        Employee employee = employeeDaoLayer.getById(employeeId);
        employee.setAddresses(null);
        employeeDaoLayer.save(employee);
    }

    @Override
    public List<Employee> searchByPinCode(int pinCode) {
        String temp = "";
        temp = temp + pinCode;
        return employeeDaoLayer.findByaddresses_PinCode(temp);
    }

    @Override
    public List<Employee> searchByCity(String city) {
        return employeeDaoLayer.findByaddresses_CityName(city);
    }

    @Override
    public List<Employee> searchByState(String state) {
        return employeeDaoLayer.findByaddresses_State(state);
    }

    @Override
    public List<Employee> searchByCountry(String country) {
        return employeeDaoLayer.findByaddresses_Country(country);
    }


}
