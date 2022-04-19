package com.example.assignment.serviceLayer;

import com.example.assignment.daoLayer.AddressDaoLayer;
import com.example.assignment.daoLayer.EmployeeDaoLayer;
import com.example.assignment.dtoLayer.AddressDto;
import com.example.assignment.dtoLayer.EmployeeDto;
import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeServiceImpl self;

    @Autowired
    Mapper dozerBeanMapper;

    @Autowired
    EmployeeDaoLayer employeeDaoLayer;






    @Override
    public List<Employee> getAllEmployeeData() {

        return employeeDaoLayer.findAll();
    }

    @Override
    @CachePut(cacheNames = "firstLevel", key = "#result.employeeId")
    public Employee addEmployee(EmployeeDto employeeDto) {
        System.out.println("dto" + employeeDto);
        System.out.println(dozerBeanMapper.map(employeeDto,Employee.class));
        return  employeeDaoLayer.save(dozerBeanMapper.map(employeeDto,Employee.class));
    }



    @Override
    @Cacheable(cacheNames = "firstLevel", key = "#employeeId" ,sync = true ,condition = "#employeeId>10")
    public Employee searchByEmployeeId(int employeeId) throws InterruptedException {
        Optional<Employee> employee = Optional.of(employeeDaoLayer.findById(employeeId).get());
//       Cache c =  cacheManager.getCache("employee");
//        Thread.sleep(5000);
        if(employee.isPresent())
        {
            return employee.get();
        }
        return  null;
    }


    @Override
    @CachePut(cacheNames = "firstLevel", key = "#employeeDto.employeeId")
    public Employee editEmployee(EmployeeDto employeeDto) {
        System.out.println("in service" + dozerBeanMapper.map(employeeDto,Employee.class));
        System.out.println(employeeDaoLayer.save(dozerBeanMapper.map(employeeDto,Employee.class)));
       return employeeDaoLayer.save(dozerBeanMapper.map(employeeDto,Employee.class));
    }



    @Override
    @CacheEvict(cacheNames = "firstLevel", key = "#employeeId")
    public boolean deleteEmployee(int employeeId) {

        employeeDaoLayer.deleteById(employeeId);
        return true;
    }

    @Override
    @CachePut(cacheNames = "firstLevel", key = "#employeeId")
    public Employee addAddress(int employeeId, AddressDto addressDto) throws InterruptedException {
        Employee employee = self.searchByEmployeeId(employeeId);
//        List<Address> addressesList = employee.getAddresses();
//        addressesList.add(address);
//        employee.setAddresses(addressesList);
        employee.addAddresses(dozerBeanMapper.map(addressDto,Address.class));
        System.out.println("in service " + employee);
        return employeeDaoLayer.save(employee);
    }

    @Override
    @CachePut(cacheNames = "firstLevel", key = "#employeeId")
    public Employee editAddress(int employeeId, AddressDto addressDto) throws InterruptedException {
        Address address = dozerBeanMapper.map(addressDto,Address.class);
        Employee employee = self.searchByEmployeeId(employeeId);
        List<Address> addressList = employee.getAddresses();
        Stream<Address> addressStream = addressList.stream().map(address1 -> {

            if(address.getAddressId() == address1.getAddressId())
            {

                return address;
            }
            return address1;
        });

      employee.setAddresses(addressStream.collect(Collectors.toList()));
        System.out.println("in service edit" + employee);
       return employeeDaoLayer.save(employee);

    }

    @Override
    @CachePut(cacheNames = "firstLevel", key = "#result.employeeId")
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
        System.out.println("in service" +  employee);
        return employeeDaoLayer.save(employee);

    }

    @Override
    @CachePut(cacheNames = "firstLevel", key = "#employeeId")
    public Employee deleteAllAddress(int employeeId) throws InterruptedException {
        Employee employee = self.searchByEmployeeId(employeeId);
        employee.setAddresses(new ArrayList<>());
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
