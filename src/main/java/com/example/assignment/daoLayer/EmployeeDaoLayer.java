package com.example.assignment.daoLayer;

import com.example.assignment.entities.Employee;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDaoLayer extends JpaRepository<Employee, Integer> {




    List<Employee> findByaddresses_PinCode(String pinCode);
    List<Employee> findByaddresses_CityName(String city);
    List<Employee> findByaddresses_State(String state);
    List<Employee> findByaddresses_Country(String country);
    Employee findByaddresses_addressId(int addressId);
}
