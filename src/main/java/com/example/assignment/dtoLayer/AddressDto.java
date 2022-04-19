package com.example.assignment.dtoLayer;

public class AddressDto {
    int addressId;
    String addressLine1;
    String cityName;
    String state;
    String country;
    String pinCode;
    String contactNo;

    public  AddressDto()
    {

    }

    public AddressDto(int addressId, String addressLine1, String cityName, String state, String country, String pinCode, String contactNo) {
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
        return "AddressDto{" +
                "addressId=" + addressId +
                ", addressLine1='" + addressLine1 + '\'' +
                ", cityName='" + cityName + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
