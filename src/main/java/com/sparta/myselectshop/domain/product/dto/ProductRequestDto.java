package com.sparta.myselectshop.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(
        @NotBlank String title,
        @NotBlank String image,
        @NotBlank String link,
        @NotNull Long lprice
) {
}
