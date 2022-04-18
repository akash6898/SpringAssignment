package com.example.assignment.serviceLayer;

import com.example.assignment.daoLayer.AddressDaoLayer;
import com.example.assignment.daoLayer.EmployeeDaoLayer;
import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    

    @Autowired
    EmployeeDaoLayer employeeDaoLayer;


    @Override
    public List<Employee> getAllEmployeeData() {

        return employeeDaoLayer.findAll();
    }

    @Override
    @CachePut(cacheNames = "searchByEmployeeId", key = "#result.employeeId")
    public Employee addEmployee(Employee employee) {
        System.out.println(employee);
        return  employeeDaoLayer.save(employee);
    }



    @Override
    @Cacheable(cacheNames = "searchByEmployeeId", key = "#employeeId" ,sync = true)
    public Employee searchByEmployeeId(int employeeId) throws InterruptedException {
        Optional<Employee> employee =  employeeDaoLayer.findById(employeeId);
        Thread.sleep(5000);
        if(employee.isPresent())
        {
            return employee.get();
        }
        return  null;
    }


    @Override
    @CachePut(cacheNames = "searchByEmployeeId", key = "#employee.employeeId")
    public Employee editEmployee(Employee employee) {
       return employeeDaoLayer.save(employee);
    }



    @Override
    @CacheEvict(cacheNames = "searchByEmployeeId", key = "#employee.employeeId")
    public void deleteEmployee(int employeeId) {

        employeeDaoLayer.deleteById(employeeId);
    }

    @Override
    @CachePut(cacheNames = "searchByEmployeeId", key = "#employeeId")
    public Employee addAddress(int employeeId, Address address) throws InterruptedException {
        Employee employee = searchByEmployeeId(employeeId);
        employee.addAddresses(address);
        return employeeDaoLayer.save(employee);
    }

    @Override
    @CachePut(cacheNames = "searchByEmployeeId", key = "#employeeId")
    public Employee editAddress(int employeeId, Address address) {
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
       return employeeDaoLayer.save(employee);
    }

    @Override
    @CachePut(cacheNames = "searchByEmployeeId", key = "#result.employeeId")
    public Employee deleteAddress(int addressId) {
        Employee employee = employeeDaoLayer.findByaddresses_addressId(addressId);
        List<Address> addressList = employee.getAddresses();
        Stream<Address> addressStream = addressList.stream().filter(address1 -> {

            if(addressId == address1.getAddressId())
            {

                return false;
            }
            return true;
        });

        employee.setAddresses(addressStream.collect(Collectors.toList()));
        System.out.println(employee.getAddresses());
        return employeeDaoLayer.save(employee);

    }

    @Override
    @CachePut(cacheNames = "searchByEmployeeId", key = "#employeeId")
    public Employee deleteAllAddress(int employeeId) {
        Employee employee = employeeDaoLayer.getById(employeeId);
        employee.setAddresses(null);
        return employeeDaoLayer.save(employee);
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
