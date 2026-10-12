package com.commercialit.bootcamp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.commercialit.bootcamp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "category")
    List<Product> findByActiveTrueOrderByNameAsc();

    @EntityGraph(attributePaths = "category")
    List<Product> findTop6ByActiveTrueOrderByIdAsc();

    @EntityGraph(attributePaths = "category")
    Optional<Product> findById(Long id);
}
