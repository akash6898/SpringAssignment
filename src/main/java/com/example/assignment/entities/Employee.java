package com.example.assignment.entities;

//import com.example.assignment.daoLayer.AddressDaoLayer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.dozer.Mapping;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Component
@Cacheable(cacheNames = "employee")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="MY_ENTITY_SEQ")
    int employeeId;
    String firstName;
    String lastName;



    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @JsonManagedReference(value = "address")
    List<Address> addresses;

    public  Employee(){
    Employee employee;
    }

    public Employee(int employeeId, String firstName, String lastName,List<Address> addresses) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getEmployeeId() == employee.getEmployeeId() && Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName()) && Objects.equals(getAddresses(), employee.getAddresses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getFirstName(), getLastName());
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
