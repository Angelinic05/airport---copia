package com.campuslands.modules.customer.application;


import java.util.List;
import com.campuslands.modules.customer.domain.Customer;
import com.campuslands.modules.customer.infrastructure.CustomerRepository;
import java.util.Optional;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }
    public Optional<Customer> findCustomerById(int id) {
        return customerRepository.findById(id);
    }
    public void deleteCustomer(int id) {
        customerRepository.delete(id);
    }
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
    
}
