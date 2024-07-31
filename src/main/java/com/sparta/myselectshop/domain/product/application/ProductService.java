package com.sparta.myselectshop.domain.product.application;

import com.sparta.myselectshop.domain.product.domain.Product;
import com.sparta.myselectshop.domain.product.dto.ProductPriceRequestDto;
import com.sparta.myselectshop.domain.product.dto.ProductRequestDto;
import com.sparta.myselectshop.domain.product.dto.ProductResponseDto;
import com.sparta.myselectshop.domain.product.repository.ProductRepository;
import com.sparta.myselectshop.global.common.ErrorCode;
import com.sparta.myselectshop.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ProductResponseDto updateProductPrice(Long id, ProductPriceRequestDto requestDto){
        Product product = productRepository.findById(id).orElseThrow( () ->
                new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
        product.updatePrice(requestDto);
        return ProductResponseDto.of(product);
    }
}
