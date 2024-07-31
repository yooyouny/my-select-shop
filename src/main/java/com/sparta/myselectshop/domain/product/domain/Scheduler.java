package com.sparta.myselectshop.domain.product.domain;

import com.sparta.myselectshop.domain.product.application.OpenApiService;
import com.sparta.myselectshop.domain.product.application.ProductService;
import com.sparta.myselectshop.domain.product.dto.ItemDto;
import com.sparta.myselectshop.domain.product.repository.ProductRepository;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j(topic = "SCHEDULER")
@Component
@RequiredArgsConstructor
public class Scheduler {
    @Qualifier("naverApiService")
    private final OpenApiService openApiService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Scheduled(cron = "0 0 1 * * *")// 초, 분, 시, 일, 월, 주 (매일 새벽 1시)
    public void updateProductPrice() {
        List<Product> productList = productRepository.findAll(); // TODO :: 의존성 질문
        productList.forEach(product -> {
            try {
                TimeUnit.SECONDS.sleep(1);

                List<ItemDto> searchedItems = openApiService.searchItems(product.getTitle());
                if(!searchedItems.isEmpty()){
                    ItemDto item = searchedItems.get(0);
                    productService.updateProductPriceByScheduler(product.getId(), item);
                    log.info("update product price : {}", product.getTitle());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();// TODO :: 인터럽트를 처리하는 주체
                log.error("Thread interrupted: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Failed to update product {}: {}", product.getId(), e.getMessage());
            }
        });
    }

}