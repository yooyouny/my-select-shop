package com.sparta.myselectshop.domain.folder.api;

import com.sparta.myselectshop.domain.folder.application.FolderService;
import com.sparta.myselectshop.domain.folder.application.ProductFolderService;
import com.sparta.myselectshop.domain.folder.dto.FolderRequestDto;
import com.sparta.myselectshop.domain.folder.dto.FolderResponseDto;
import com.sparta.myselectshop.domain.user.domain.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;
    private final ProductFolderService productFolderService;

    @PostMapping("/folders")
    public void addFolders(@RequestBody FolderRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        folderService.addFolders(requestDto.folderNames(), userDetails.getUser());
    }

    @GetMapping("/folders")
    public List<FolderResponseDto> getFolders(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return folderService.getFolders(userDetails.getUser());
    }

    @PostMapping("/products/{productId}/folder")
    public void addFolder(
            @PathVariable Long productId,
            @RequestParam Long folderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        productFolderService.addFolder(productId, folderId, userDetails.getUser());
    }
}
