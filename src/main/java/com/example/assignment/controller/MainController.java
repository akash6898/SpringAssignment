package com.example.assignment.controller;

import com.example.assignment.dtoLayer.AddressDto;
import com.example.assignment.dtoLayer.EmployeeDto;
//import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;
import com.example.assignment.execption.CustomExecption;
import com.example.assignment.serviceLayer.EmployeeService;
import com.example.assignment.serviceLayer.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/sortedEmployeeWithPagination")
    public Page<Employee> getAllEmployee(@RequestParam("pageNo") int pageNo , @RequestParam("pageSize") int pageSize)
    {

        return employeeService.sortedEmployeeWithPagination(pageNo,pageSize);
    }


    @PostMapping("/employee")
    public ResponseEntity addEmployee(@RequestBody EmployeeDto employeeDto)
    {
        employeeService.addEmployee(employeeDto);
       return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/bulkEmployee")
    public ResponseEntity addBulkEmployee(@RequestBody List<EmployeeDto> employeeDtoList)
    {
        ExecutorService service = Executors.newFixedThreadPool(8);
        employeeDtoList.forEach(employeeDto -> {

            service.execute(() ->employeeService.addEmployee(employeeDto));
        });
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId) throws InterruptedException, CustomExecption {

        return employeeService.searchByEmployeeId(employeeId);
    }

    @PutMapping("/employee")
    public ResponseEntity editEmployee(@RequestBody EmployeeDto employeeDto) throws CustomExecption {
        employeeService.editEmployee(employeeDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable("employeeId") int employeeId)
    {

        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/address/{employeeId}")
    public ResponseEntity addAddress(@PathVariable("employeeId") int employeeId , @RequestBody AddressDto addressDto) throws InterruptedException, CustomExecption {
        employeeService.addAddress(employeeId , addressDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/address/{employeeId}")
    public ResponseEntity editAddress(@PathVariable("employeeId") int employeeId , @RequestBody AddressDto addressDto) throws InterruptedException, CustomExecption {
        employeeService.editAddress(employeeId , addressDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity deleteAddress(@PathVariable("addressId") int addressId)
    {
        employeeService.deleteAddress(addressId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllAddress/{employeeId}")
    public ResponseEntity deleteAllAddress(@PathVariable("employeeId") int employeeId) throws InterruptedException, CustomExecption {
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
