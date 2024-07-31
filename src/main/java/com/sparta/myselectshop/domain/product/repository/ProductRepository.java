package com.sparta.myselectshop.domain.product.repository;

import com.sparta.myselectshop.domain.product.domain.Product;
import com.sparta.myselectshop.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUser(User user);
}
