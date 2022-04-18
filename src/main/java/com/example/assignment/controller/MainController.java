package com.example.assignment.controller;

import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;
import com.example.assignment.execption.CustomExecption;
import com.example.assignment.serviceLayer.EmployeeService;
import com.example.assignment.serviceLayer.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MainController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getAllEmployee()
    {

        return employeeService.getAllEmployeeData();
    }


    @PostMapping("/employee")
    public ResponseEntity addEmployee(@RequestBody Employee employee)
    {
        employeeService.addEmployee(employee);
       return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/bulkEmployee")
    public ResponseEntity addBulkEmployee(@RequestBody List<Employee> employeeList)
    {
        ExecutorService service = Executors.newFixedThreadPool(8);
        employeeList.forEach(employee -> {

            service.execute(() ->employeeService.addEmployee(employee));
        });
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId) throws InterruptedException {

        return employeeService.searchByEmployeeId(employeeId);
    }

    @PutMapping("/employee")
    public ResponseEntity editEmployee(@RequestBody Employee employee)
    {
        employeeService.editEmployee(employee);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable("employeeId") int employeeId)
    {

        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/address/{employeeId}")
    public ResponseEntity addAddress(@PathVariable("employeeId") int employeeId , @RequestBody Address address) throws InterruptedException {
        employeeService.addAddress(employeeId , address);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/address/{employeeId}")
    public ResponseEntity editAddress(@PathVariable("employeeId") int employeeId , @RequestBody Address address) throws InterruptedException {
        employeeService.editAddress(employeeId , address);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity deleteAddress(@PathVariable("addressId") int addressId)
    {
        employeeService.deleteAddress(addressId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllAddress/{employeeId}")
    public ResponseEntity deleteAllAddress(@PathVariable("employeeId") int employeeId) throws InterruptedException {
        employeeService.deleteAllAddress(employeeId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/searchByPinCode/{pinCode}")
    public List<Employee> searchByPinCode(@PathVariable("pinCode") int pinCode)
    {

        return employeeService.searchByPinCode(pinCode);
    }

    @GetMapping("/searchByCity/{city}")
    public List<Employee> searchByCity(@PathVariable("city") String city)
    {

        return employeeService.searchByCity(city);
    }

    @GetMapping("/searchByState/{state}")
    public List<Employee> searchByState(@PathVariable("state") String state)
    {

        return employeeService.searchByState(state);
    }

    @GetMapping("/searchByCountry/{country}")
    public List<Employee> searchByCountry(@PathVariable("country") String country)  {


        return employeeService.searchByCountry(country);
    }

}
