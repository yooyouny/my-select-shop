package com.sparta.myselectshop.domain.product.repository;

import com.sparta.myselectshop.domain.product.domain.Product;
import com.sparta.myselectshop.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUser(User user, Pageable pageable);
}
