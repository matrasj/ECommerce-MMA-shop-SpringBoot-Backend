package com.example.ecommercebackend.repository;

import com.example.ecommercebackend.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByProductCategoryId(@Param("productCategoryId") Long productCategoryId, Pageable pageable);
    Page<Product> findByNameContaining(@Param("keyword") String keyword, Pageable pageable);
}
