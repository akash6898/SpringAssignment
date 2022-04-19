package com.example.assignment;


import com.example.assignment.daoLayer.EmployeeDaoLayer;
import com.example.assignment.entities.Address;
import com.example.assignment.entities.Employee;
import com.example.assignment.serviceLayer.EmployeeService;

import com.example.assignment.serviceLayer.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentApplicationTests {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private  EmployeeDaoLayer employeeDaoLayer;





    Address address1 = new Address(1,"katra Bazar","jhansi","Up","India","284403" , "9794335345");
    Address address2 = new Address(1,"street 6","noida","Up","India","460052" , "965552662");
    Address address3 = new Address(3,"street 8","new York","washington dc","Usa","460052" , "9322342322");

    Employee akash = new Employee(1, "Akash", "Soni", Stream.of(address1 ).collect(Collectors.toList()));

    Employee aman = new Employee(2, "aman", "Soni", Stream.of(address2).collect(Collectors.toList()));
    Employee akansha = new Employee(2, "akansha", "Soni", Stream.of(address3).collect(Collectors.toList()));


    @Test
    public void editAddressTest() throws InterruptedException, CloneNotSupportedException {

        Employee newAkash = new Employee(akash.getEmployeeId(), akash.getFirstName(),akash.getLastName(),Stream.of(address2).collect(Collectors.toList()));
        given(employeeDaoLayer.findById(1)).willReturn(Optional.of(akash));
        given(employeeDaoLayer.save(newAkash)).willReturn(newAkash);
        System.out.println("in test" + newAkash);
        assertEquals(newAkash, employeeService.editAddress(1,address2));
        verify(employeeDaoLayer).save(newAkash);
    }

    @Test
    public void getAllEmployeeDataTest() {

        when(employeeDaoLayer.findAll()).thenReturn(Stream
                .of(akash).collect(Collectors.toList()));
        assertEquals(Stream
                .of(akash).collect(Collectors.toList()), employeeService.getAllEmployeeData());
        verify(employeeDaoLayer).findAll();
    }

    @Test
    public void addEmployeeTest(){

        when(employeeDaoLayer.save(akash)).thenReturn(akash);
        assertEquals(akash, employeeService.addEmployee(akash));
        verify(employeeDaoLayer).save(akash);
    }

    @Test
    public void searchByEmployeeIdTest() throws InterruptedException {

        when(employeeDaoLayer.findById(2)).thenReturn(Optional.of(aman));
        assertEquals(aman, employeeService.searchByEmployeeId(2));
        verify(employeeDaoLayer).findById(2);
    }

    @Test
    public void editEmployeeTest(){

        when(employeeDaoLayer.save(akash)).thenReturn(akash);
        assertEquals(akash, employeeService.editEmployee(akash));
        verify(employeeDaoLayer).save(akash);
    }

    @Test
    public void deleteEmployee()
    {

    }

    @Test
    public void addAddressTest() throws InterruptedException, CloneNotSupportedException {

        Employee newAkash =new Employee(akash.getEmployeeId(), akash.getFirstName(),akash.getLastName(), Stream.of(address1).collect(Collectors.toList()));
        List<Address> addressList = newAkash.getAddresses();
        addressList.add(address3);
        newAkash.setAddresses(addressList);
        when(employeeDaoLayer.findById(1)).thenReturn(Optional.of(akash));
        when(employeeDaoLayer.save(newAkash)).thenReturn(newAkash);
        System.out.println("in test" + newAkash);
        assertEquals(newAkash, employeeService.addAddress(1,address3));
        verify(employeeDaoLayer).save(newAkash);
    }



    @Test
    public void deleteAddressTest() throws CloneNotSupportedException {

        System.out.println("delete address");
        System.out.println( "in test 0" +akash);
        Employee newAkash = new Employee(akash.getEmployeeId(), akash.getFirstName(),akash.getLastName(),Stream.of(address1).collect(Collectors.toList()));
        newAkash.addAddresses(address3);
        when(employeeDaoLayer.findByaddresses_addressId(3)).thenReturn(newAkash);
        when(employeeDaoLayer.save(akash)).thenReturn(akash);
        assertEquals(akash, employeeService.deleteAddress(3));
        verify(employeeDaoLayer).save(akash);

    }

    @Test
    public void deleteAllAddressTest() throws InterruptedException, CloneNotSupportedException {

        Employee newAkash2 = new Employee(akash.getEmployeeId(), akash.getFirstName(),akash.getLastName(),Stream.of(address1).collect(Collectors.toList()));
        newAkash2.addAddresses(address3);
        newAkash2.addAddresses(address2);
        Employee newAkash = new Employee(akash.getEmployeeId(), akash.getFirstName(),akash.getLastName(),Stream.of(address1).collect(Collectors.toList()));
        newAkash.setAddresses(new ArrayList<Address>());
        when(employeeDaoLayer.findById(1)).thenReturn(Optional.of(newAkash2));
        when(employeeDaoLayer.save(newAkash)).thenReturn(newAkash);
        assertEquals(newAkash, employeeService.deleteAllAddress(1));
        verify(employeeDaoLayer).save(newAkash);
    }

    @Test
    public void searchByPinCodeTest(){

        when(employeeDaoLayer.findByaddresses_PinCode("284403")).thenReturn(Stream.of(akash).collect(Collectors.toList()));
        assertEquals(Stream.of(akash).collect(Collectors.toList()), employeeService.searchByPinCode(284403));
        verify(employeeDaoLayer).findByaddresses_PinCode("284403");
    }

    @Test
    public void searchByCityTest(){

        when(employeeDaoLayer.findByaddresses_CityName("jhansi")).thenReturn(Stream.of(akash).collect(Collectors.toList()));
        assertEquals(Stream.of(akash).collect(Collectors.toList()), employeeService.searchByCity("jhansi"));
        verify(employeeDaoLayer).findByaddresses_CityName("jhansi");
    }

    @Test
    public void searchByStateTest(){

        when(employeeDaoLayer.findByaddresses_State("Up")).thenReturn(Stream.of(akash).collect(Collectors.toList()));
        assertEquals(Stream.of(akash).collect(Collectors.toList()), employeeService.searchByState("Up"));
        verify(employeeDaoLayer).findByaddresses_State("Up");
    }

    @Test
    public void searchByCountryTest(){

        when(employeeDaoLayer.findByaddresses_Country("Usa")).thenReturn(Stream.of(akansha).collect(Collectors.toList()));
        assertEquals(Stream.of(akansha).collect(Collectors.toList()), employeeService.searchByCountry("Usa"));
        verify(employeeDaoLayer).findByaddresses_Country("Usa");
    }

}
