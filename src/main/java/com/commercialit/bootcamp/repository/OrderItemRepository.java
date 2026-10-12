package com.commercialit.bootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.commercialit.bootcamp.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
