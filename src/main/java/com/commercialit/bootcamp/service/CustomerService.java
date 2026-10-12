package com.commercialit.bootcamp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commercialit.bootcamp.exception.ResourceNotFoundException;
import com.commercialit.bootcamp.model.Customer;
import com.commercialit.bootcamp.repository.CustomerRepository;

@Service
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAllByOrderByLastNameAscFirstNameAsc();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " was not found"));
    }

    public long count() {
        return customerRepository.count();
    }
}
