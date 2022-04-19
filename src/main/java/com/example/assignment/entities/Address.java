package com.example.assignment.entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Cacheable(cacheNames = "employee")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="MY_ENTITY_SEQ")
    int addressId;
    String addressLine1;
    String cityName;
    String state;
    String country;
    String pinCode;
    String contactNo;



    public Address()
    {

    }

    public Address(int addressId, String addressLine1, String cityName, String state, String country, String pinCode, String contactNo) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.cityName = cityName;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.contactNo = contactNo;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", addressLine1='" + addressLine1 + '\'' +
                ", cityName='" + cityName + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressId == address.addressId && addressLine1.equals(address.addressLine1) && cityName.equals(address.cityName) && state.equals(address.state) && country.equals(address.country) && pinCode.equals(address.pinCode) && contactNo.equals(address.contactNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, addressLine1, cityName, state, country, pinCode, contactNo);
    }
}
