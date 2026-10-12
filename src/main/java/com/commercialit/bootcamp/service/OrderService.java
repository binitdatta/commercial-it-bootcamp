package com.commercialit.bootcamp.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commercialit.bootcamp.dto.CreateOrderRequest;
import com.commercialit.bootcamp.dto.OrderLineRequest;
import com.commercialit.bootcamp.exception.ResourceNotFoundException;
import com.commercialit.bootcamp.model.Customer;
import com.commercialit.bootcamp.model.CustomerOrder;
import com.commercialit.bootcamp.model.OrderItem;
import com.commercialit.bootcamp.model.OrderStatus;
import com.commercialit.bootcamp.model.Product;
import com.commercialit.bootcamp.repository.CustomerOrderRepository;
import com.commercialit.bootcamp.repository.CustomerRepository;
import com.commercialit.bootcamp.repository.ProductRepository;

@Service
public class OrderService {
    private static final BigDecimal TAX_RATE = new BigDecimal("0.0700");
    private static final BigDecimal SHIPPING = new BigDecimal("9.95");
    private static final BigDecimal FREE_SHIPPING_THRESHOLD = new BigDecimal("100.00");

    private final CustomerOrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(CustomerOrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<CustomerOrder> findAll() {
        return orderRepository.findAllByOrderByPlacedAtDesc();
    }

    @Transactional(readOnly = true)
    public CustomerOrder findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order " + id + " was not found"));
    }

    @Transactional(readOnly = true)
    public long count() {
        return orderRepository.count();
    }

    @Transactional
    public CustomerOrder createOrder(CreateOrderRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer " + request.customerId() + " was not found"));

        CustomerOrder order = new CustomerOrder();
        order.setOrderNumber("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.CREATED);

        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderLineRequest line : request.lines()) {
            Product product = productRepository.findById(line.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product " + line.productId() + " was not found"));

            if (!product.isActive()) {
                throw new IllegalArgumentException("Product " + product.getSku() + " is not active");
            }
            if (product.getStockQuantity() < line.quantity()) {
                throw new IllegalArgumentException("Insufficient inventory for product " + product.getSku());
            }

            BigDecimal lineTotal = product.getPrice().multiply(BigDecimal.valueOf(line.quantity()));
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(line.quantity());
            item.setUnitPrice(product.getPrice());
            item.setLineTotal(lineTotal);
            order.addItem(item);

            product.setStockQuantity(product.getStockQuantity() - line.quantity());
            subtotal = subtotal.add(lineTotal);
        }

        BigDecimal tax = subtotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
        BigDecimal shipping = subtotal.compareTo(FREE_SHIPPING_THRESHOLD) >= 0 ? BigDecimal.ZERO : SHIPPING;
        order.setSubtotal(subtotal.setScale(2, RoundingMode.HALF_UP));
        order.setTaxAmount(tax);
        order.setShippingAmount(shipping);
        order.setTotalAmount(subtotal.add(tax).add(shipping).setScale(2, RoundingMode.HALF_UP));

        CustomerOrder saved = orderRepository.save(order);
        return orderRepository.findById(saved.getId()).orElseThrow();
    }
}
