package com.sparta.myselectshop.domain.product.repository;

import com.sparta.myselectshop.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
