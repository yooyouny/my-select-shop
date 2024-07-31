package com.sparta.myselectshop.domain.folder.api;

import com.sparta.myselectshop.domain.folder.application.FolderService;
import com.sparta.myselectshop.domain.folder.dto.FolderRequestDto;
import com.sparta.myselectshop.domain.user.domain.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;

    @PostMapping("/folders")
    public void addFolders(@RequestBody FolderRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        folderService.addFolders(requestDto.folderNames(), userDetails.getUser());
    }
}
