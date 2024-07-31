package com.sparta.myselectshop.domain.product.dto;

import com.sparta.myselectshop.domain.product.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductResponseDto(
        @NotNull Long id,
        @NotBlank String title,
        @NotBlank String image,
        @NotBlank String link,
        @NotNull Long lprice,
        int myPrice
) {
    public static ProductResponseDto of(Product product){
        return new ProductResponseDto(
                product.getId(),
                product.getTitle(),
                product.getImage(),
                product.getLink(),
                product.getLprice(),
                product.getMyPrice()
        );
    }
}
