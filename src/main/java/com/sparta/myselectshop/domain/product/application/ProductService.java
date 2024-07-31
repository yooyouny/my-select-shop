package com.sparta.myselectshop.domain.product.application;

import com.sparta.myselectshop.domain.product.domain.Product;
import com.sparta.myselectshop.domain.product.dto.ItemDto;
import com.sparta.myselectshop.domain.product.dto.ProductPriceRequestDto;
import com.sparta.myselectshop.domain.product.dto.ProductRequestDto;
import com.sparta.myselectshop.domain.product.dto.ProductResponseDto;
import com.sparta.myselectshop.domain.product.repository.ProductRepository;
import com.sparta.myselectshop.domain.user.domain.User;
import com.sparta.myselectshop.global.common.ErrorCode;
import com.sparta.myselectshop.global.common.exception.CustomException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto, User user){
        Product product = Product.fromDto(requestDto, user);
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

    @Transactional
    public void updateProductPriceByScheduler(Long id, ItemDto itemDto){
        Product product = productRepository.findById(id).orElseThrow( () ->
                new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
        product.updatePrice(itemDto);
    }

    public List<ProductResponseDto> getProducts(User user){
        List<Product> productList = productRepository.findAllByUser(user);
        return productList.stream()
                .map(product -> ProductResponseDto.of(product))
                .collect(Collectors.toList());
    }

    public List<ProductResponseDto> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product -> ProductResponseDto.of(product))
                .collect(Collectors.toList());
    }
}
