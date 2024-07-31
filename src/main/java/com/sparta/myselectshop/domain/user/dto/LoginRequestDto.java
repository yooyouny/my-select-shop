package com.sparta.myselectshop.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "로그인 시 유저이름은 필수입니다")
        String username,
        @NotBlank(message = "로그인 시 패스워드는 필수입니다")
        String password
) {
}
