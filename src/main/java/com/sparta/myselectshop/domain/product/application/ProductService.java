package com.sparta.myselectshop.domain.product.application;

import com.sparta.myselectshop.domain.product.domain.Product;
import com.sparta.myselectshop.domain.product.dto.ProductRequestDto;
import com.sparta.myselectshop.domain.product.dto.ProductResponseDto;
import com.sparta.myselectshop.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto){
        Product product = Product.fromDto(requestDto);
        productRepository.save(product);
        return ProductResponseDto.of(product);
    }
}
