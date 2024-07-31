package com.sparta.myselectshop.domain.product.api;

import com.sparta.myselectshop.domain.product.application.ProductService;
import com.sparta.myselectshop.domain.product.dto.ProductPriceRequestDto;
import com.sparta.myselectshop.domain.product.dto.ProductRequestDto;
import com.sparta.myselectshop.domain.product.dto.ProductResponseDto;
import com.sparta.myselectshop.domain.user.domain.UserDetailsImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ProductResponseDto createProduct(@RequestBody @Valid ProductRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(requestDto, userDetails.getUser());
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody @Valid ProductPriceRequestDto requestDto) {
        return productService.updateProductPrice(id, requestDto);
    }

    @GetMapping
    public List<ProductResponseDto> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return productService.getProducts(userDetails.getUser());
    }
}
