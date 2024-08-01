package com.sparta.myselectshop.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.json.JSONObject;

public record ItemDto(
        @NotBlank String title,
        @NotBlank String link,
        @NotBlank String image,
        @NotNull Long lprice
) {
    public ItemDto(JSONObject object) {
        this(
                object.getString("title"),
                object.getString("link"),
                object.getString("image"),
                object.getLong("lprice")
        );
    }
}
