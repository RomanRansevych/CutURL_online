package com.company.cuturl.services.customer;

import com.company.cuturl.entities.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<String> getAllLogin();

    Customer getCustomerByLogin(String login);

    void createCustomer(Customer customer);

    Optional<Customer> getCustomer(int id);

    void deleteCustomer(int id);
}
