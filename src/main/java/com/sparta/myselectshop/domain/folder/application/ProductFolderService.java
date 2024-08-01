package com.sparta.myselectshop.domain.folder.application;

import com.sparta.myselectshop.domain.folder.domain.Folder;
import com.sparta.myselectshop.domain.folder.domain.ProductFolder;
import com.sparta.myselectshop.domain.folder.repository.FolderRepository;
import com.sparta.myselectshop.domain.folder.repository.ProductFolderRepository;
import com.sparta.myselectshop.domain.product.domain.Product;
import com.sparta.myselectshop.domain.product.repository.ProductRepository;
import com.sparta.myselectshop.domain.user.domain.User;
import com.sparta.myselectshop.global.common.ErrorCode;
import com.sparta.myselectshop.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductFolderService {
    private final ProductRepository productRepository;
    private final FolderRepository folderRepository;
    private final ProductFolderRepository productFolderRepository;

    public void addFolder(Long productId, Long folderId, User user) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
        Folder folder = folderRepository.findById(folderId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_FOLDER));
        checkOwnership(product, folder, user);
        productFolderRepository.findByProductAndFolder(product, folder).ifPresent(existingProductFolder -> {
            throw new CustomException(ErrorCode.DUPLICATED_FOLDER);
        });

        productFolderRepository.save(ProductFolder.of(product, folder));
    }

    private void checkOwnership(Product product, Folder folder, User user) {
        if (product.getId() != user.getId() || folder.getUser().getId() != user.getId()) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
    }
}
