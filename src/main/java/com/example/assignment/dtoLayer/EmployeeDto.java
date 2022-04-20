package com.example.assignment.dtoLayer;

import com.example.assignment.entities.Address;
import org.dozer.Mapping;

import java.util.List;

public class EmployeeDto {
    int employeeId;
    String firstName;
    String lastName;

    List<AddressDto> addressesDtoList;

    public EmployeeDto()
    {

    }

    public EmployeeDto(int employeeId, String firstName, String lastName, List<AddressDto> addressesDtoList) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressesDtoList = addressesDtoList;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Mapping("addresses")
    public List<AddressDto> getAddressesDtoList() {
        return addressesDtoList;
    }

    public void setAddressesDtoList(List<AddressDto> addressesDtoList) {
        this.addressesDtoList = addressesDtoList;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressesDtoList=" + addressesDtoList +
                '}';
    }
}
