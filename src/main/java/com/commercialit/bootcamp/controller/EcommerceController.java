package com.commercialit.bootcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.commercialit.bootcamp.service.CustomerService;
import com.commercialit.bootcamp.service.OrderService;
import com.commercialit.bootcamp.service.ProductService;

@Controller
@RequestMapping("/ecommerce")
public class EcommerceController {
    private final ProductService productService;
    private final CustomerService customerService;
    private final OrderService orderService;

    public EcommerceController(ProductService productService,
                               CustomerService customerService,
                               OrderService orderService) {
        this.productService = productService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("productCount", productService.count());
        model.addAttribute("customerCount", customerService.count());
        model.addAttribute("orderCount", orderService.count());
        model.addAttribute("featuredProducts", productService.findFeatured());
        return "ecommerce/index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAllActive());
        return "ecommerce/products";
    }

    @GetMapping("/products/{id}")
    public String product(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "ecommerce/product-detail";
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "ecommerce/customers";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "ecommerce/orders";
    }

    @GetMapping("/orders/{id}")
    public String order(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "ecommerce/order-detail";
    }

    @GetMapping("/architecture")
    public String architecture() {
        return "ecommerce/architecture";
    }

    @GetMapping("/api-guide")
    public String apiGuide() {
        return "ecommerce/api-guide";
    }
}
