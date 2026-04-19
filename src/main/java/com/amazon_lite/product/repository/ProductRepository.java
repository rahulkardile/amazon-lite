package com.amazon_lite.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amazon_lite.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
