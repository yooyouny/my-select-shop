package com.sparta.myselectshop.domain.folder.dto;

import com.sparta.myselectshop.domain.folder.domain.Folder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FolderResponseDto {
    private Long id;
    private String name;

    public static FolderResponseDto of(Folder folder) {
        return new FolderResponseDto(folder.getId(), folder.getName());
    }
}
