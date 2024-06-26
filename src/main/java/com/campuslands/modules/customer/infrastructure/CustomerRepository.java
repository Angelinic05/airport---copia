package com.campuslands.modules.customer.infrastructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.customer.domain.Customer;

public interface CustomerRepository {
    void save(Customer customer);
    void update(Customer customer);
    Optional<Customer> findById(int id);
    void delete(int id);
    List<Customer> findAll();
}
