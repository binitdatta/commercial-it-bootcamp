package com.commercialit.bootcamp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.commercialit.bootcamp.model.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    @EntityGraph(attributePaths = "customer")
    List<CustomerOrder> findAllByOrderByPlacedAtDesc();

    @EntityGraph(attributePaths = {"customer", "items", "items.product", "items.product.category"})
    Optional<CustomerOrder> findById(Long id);

    Optional<CustomerOrder> findByOrderNumber(String orderNumber);
}
