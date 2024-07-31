package com.sparta.myselectshop.domain.product.dto;

import jakarta.validation.constraints.Min;

public record ProductPriceRequestDto(
        @Min(value = MIN_PRICE, message = "유효하지 않은 관심 가격입니다") int myprice
) {
    static final int MIN_PRICE = 100;
}
