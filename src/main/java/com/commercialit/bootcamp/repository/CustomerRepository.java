package com.commercialit.bootcamp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.commercialit.bootcamp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailIgnoreCase(String email);
    List<Customer> findAllByOrderByLastNameAscFirstNameAsc();
}
