package com.sparta.myselectshop.domain.product.domain;

import com.sparta.myselectshop.domain.folder.domain.ProductFolder;
import com.sparta.myselectshop.domain.product.dto.ItemDto;
import com.sparta.myselectshop.domain.product.dto.ProductPriceRequestDto;
import com.sparta.myselectshop.domain.product.dto.ProductRequestDto;
import com.sparta.myselectshop.domain.user.domain.User;
import com.sparta.myselectshop.global.common.DateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String title;
    @Column
    @NotBlank
    private String image;
    @Column
    @NotBlank
    private String link;
    @Column
    @NotNull
    private Long lprice;
    @Column
    private int myPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "product")
    private List<ProductFolder> productFolderList = new ArrayList<>();

    @Builder
    public Product(String title, String image, String link, Long lprice, User user) {
        this.title = title;
        this.image = image;
        this.link = link;
        this.lprice = lprice;
        this.user = user;
    }

    public static Product fromDto(ProductRequestDto requestDto, User user) {
        return Product.builder()
                .title(requestDto.title())
                .image(requestDto.image())
                .link(requestDto.link())
                .lprice(requestDto.lprice())
                .user(user)
                .build();
    }

    public static Product fromItemDto(ItemDto itemDto, User user) {
        return Product.builder()
                .title(itemDto.title())
                .image(itemDto.image())
                .link(itemDto.link())
                .lprice(itemDto.lprice())
                .user(user)
                .build();
    }

    public void updatePrice(ProductPriceRequestDto requestDto) {
        this.myPrice = requestDto.myprice();
    }

    public void updatePrice(ItemDto itemDto) {
        this.lprice = itemDto.lprice();
    }
}
