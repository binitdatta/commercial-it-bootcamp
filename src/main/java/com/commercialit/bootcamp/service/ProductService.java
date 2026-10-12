package com.commercialit.bootcamp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commercialit.bootcamp.exception.ResourceNotFoundException;
import com.commercialit.bootcamp.model.Product;
import com.commercialit.bootcamp.repository.ProductRepository;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllActive() {
        return productRepository.findByActiveTrueOrderByNameAsc();
    }

    public List<Product> findFeatured() {
        return productRepository.findTop6ByActiveTrueOrderByIdAsc();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " was not found"));
    }

    public long count() {
        return productRepository.count();
    }
}
