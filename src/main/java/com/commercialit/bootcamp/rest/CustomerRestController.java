package com.commercialit.bootcamp.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercialit.bootcamp.model.Customer;
import com.commercialit.bootcamp.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerSummary> customers() {
        return customerService.findAll().stream()
                .map(c -> new CustomerSummary(c.getId(), c.getFullName(), c.getEmail(), c.getPhone()))
                .toList();
    }

    public record CustomerSummary(Long id, String name, String email, String phone) { }
}
