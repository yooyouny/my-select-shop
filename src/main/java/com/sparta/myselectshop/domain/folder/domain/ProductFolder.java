package com.sparta.myselectshop.domain.folder.domain;

import com.sparta.myselectshop.domain.product.domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product_folter")
public class ProductFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    @NotNull
    private Folder folder;

    @Builder
    public ProductFolder(Product product, Folder folder) {
        this.product = product;
        this.folder = folder;
    }

    public static ProductFolder of(Product product, Folder folder){
        return ProductFolder
                .builder()
                .product(product)
                .folder(folder)
                .build();
    }
}
