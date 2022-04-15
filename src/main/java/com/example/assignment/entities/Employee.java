package com.example.assignment.entities;

import com.example.assignment.daoLayer.AddressDaoLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="MY_ENTITY_SEQ")
    int employeeId;
    String firstName;
    String lastName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    List<Address> addresses;

    public  Employee(){
    Employee employee;
    }

    public Employee(int employeeId, String firstName, String lastName, List<Address> addresses) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = addresses;
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

    public List<Address> getAddresses() {

        return addresses;
    }

    public void setAddresses(List<Address> addresses) {

        this.addresses = addresses;
    }

    public void addAddresses(Address address) {

        addresses.add(address);
    }




    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
