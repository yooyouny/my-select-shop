package com.sparta.myselectshop.domain.product.application;

import com.sparta.myselectshop.domain.product.dto.ItemDto;
import java.util.List;

public interface OpenApiService {
    List<ItemDto> searchItems(String query);
}
