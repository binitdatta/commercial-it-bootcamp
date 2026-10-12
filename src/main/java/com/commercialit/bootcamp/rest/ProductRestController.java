package com.commercialit.bootcamp.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercialit.bootcamp.dto.ProductResponse;
import com.commercialit.bootcamp.model.Product;
import com.commercialit.bootcamp.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> products() {
        return productService.findAllActive().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public ProductResponse product(@PathVariable Long id) {
        return toResponse(productService.findById(id));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getSku(), product.getName(),
                product.getCategory().getName(), product.getPrice(), product.getStockQuantity(), product.isActive());
    }
}
