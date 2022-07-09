package com.company.cuturl.repositories;

import com.company.cuturl.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Modifying
    @Transactional
    @Query(value = "SELECT login FROM customers", nativeQuery = true)
    List<String> getAllLogin();

    Customer getCustomerByLogin(String login);
}
