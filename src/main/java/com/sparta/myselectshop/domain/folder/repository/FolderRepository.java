package com.sparta.myselectshop.domain.folder.repository;

import com.sparta.myselectshop.domain.folder.domain.Folder;
import com.sparta.myselectshop.domain.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);
    List<Folder> findAllByUser(User user);
}
