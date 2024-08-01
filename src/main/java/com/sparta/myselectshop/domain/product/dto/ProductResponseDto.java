package com.sparta.myselectshop.domain.product.dto;

import com.sparta.myselectshop.domain.folder.domain.ProductFolder;
import com.sparta.myselectshop.domain.folder.dto.FolderResponseDto;
import com.sparta.myselectshop.domain.product.domain.Product;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private String link;
    private String image;
    private Long lprice;
    private int myprice;
    private List<FolderResponseDto> productFolderList = new ArrayList<>();

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.link = product.getLink();
        this.image = product.getImage();
        this.lprice = product.getLprice();
        this.myprice = product.getMyPrice();
        for (ProductFolder productFolder : product.getProductFolderList()) {
            productFolderList.add(
                    new FolderResponseDto(productFolder.getFolder().getId(), productFolder.getFolder().getName()));
        }
    }
}
