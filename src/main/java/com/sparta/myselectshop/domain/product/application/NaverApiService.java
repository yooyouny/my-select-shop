package com.sparta.myselectshop.domain.product.application;

import com.sparta.myselectshop.domain.product.dto.ItemDto;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j(topic = "NAVER API")
public class NaverApiService implements OpenApiService{
    private final RestTemplate restTemplate;
    @Value("${openapi.naver.client-id}")
    private String clientId;
    @Value("${openapi.naver.client-secret}")
    private String clientSecret;

    public NaverApiService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }
    public List<ItemDto> searchItems(String query){
        URI uri = getUri(query);
        log.info("uri = {}", uri);
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        log.info("status code : {}", responseEntity.getStatusCode());
        return fromJsonToItems(responseEntity.getBody());
    }
    public static List<ItemDto> fromJsonToItems(String responseStr){
        JSONArray items = new JSONObject(responseStr).getJSONArray("items");
        List<ItemDto> itemList = new ArrayList<>();
        for(Object o : items){
            itemList.add(new ItemDto((JSONObject) o));
        }
        return itemList;
    }
    private URI getUri(String query){
        return UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/shop.json")
                .queryParam("display", 15)
                .queryParam("query", query)
                .encode()
                .build()
                .toUri();
    }



}
