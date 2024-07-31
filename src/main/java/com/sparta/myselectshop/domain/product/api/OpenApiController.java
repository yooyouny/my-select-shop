package com.sparta.myselectshop.domain.product.api;

import com.sparta.myselectshop.domain.product.application.NaverApiService;
import com.sparta.myselectshop.domain.product.dto.ItemDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OpenApiController {
    private final NaverApiService naverApiService;
    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam(name = "query") String query){
        return naverApiService.searchItems(query);
    }
}
