package com.commercialit.bootcamp.rest;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.commercialit.bootcamp.dto.CreateOrderRequest;
import com.commercialit.bootcamp.dto.OrderResponse;
import com.commercialit.bootcamp.model.CustomerOrder;
import com.commercialit.bootcamp.model.OrderItem;
import com.commercialit.bootcamp.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponse> orders() {
        return orderService.findAll().stream()
                .map(order -> new OrderResponse(order.getId(), order.getOrderNumber(), order.getCustomer().getFullName(),
                        order.getStatus().name(), order.getSubtotal(), order.getTaxAmount(), order.getShippingAmount(),
                        order.getTotalAmount(), order.getPlacedAt(), List.of()))
                .toList();
    }

    @GetMapping("/{id}")
    public OrderResponse order(@PathVariable Long id) {
        return toResponse(orderService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@Valid @RequestBody CreateOrderRequest request) {
        return toResponse(orderService.createOrder(request));
    }

    private OrderResponse toResponse(CustomerOrder order) {
        List<OrderResponse.OrderItemResponse> items = order.getItems().stream().map(this::toItem).toList();
        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getCustomer().getFullName(),
                order.getStatus().name(), order.getSubtotal(), order.getTaxAmount(), order.getShippingAmount(),
                order.getTotalAmount(), order.getPlacedAt(), items);
    }

    private OrderResponse.OrderItemResponse toItem(OrderItem item) {
        return new OrderResponse.OrderItemResponse(item.getProduct().getId(), item.getProduct().getSku(),
                item.getProduct().getName(), item.getQuantity(), item.getUnitPrice(), item.getLineTotal());
    }
}
