package com.company.cuturl.services.customer;

import com.company.cuturl.entities.Customer;
import com.company.cuturl.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplements implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<String> getAllLogin() {
        return customerRepository.getAllLogin();
    }

    @Override
    public Customer getCustomerByLogin(String login) {
        return customerRepository.getCustomerByLogin(login);
    }

    @Override
    public void createCustomer(Customer customer) {
        customer.setRole("ROLE_CUSTOMER");
        customer.setEnabled(true);

        customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomer(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
