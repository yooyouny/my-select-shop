package com.sparta.myselectshop.domain.folder.repository;

import com.sparta.myselectshop.domain.folder.domain.Folder;
import com.sparta.myselectshop.domain.folder.domain.ProductFolder;
import com.sparta.myselectshop.domain.product.domain.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
