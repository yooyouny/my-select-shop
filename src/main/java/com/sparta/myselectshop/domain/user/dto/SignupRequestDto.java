package com.sparta.myselectshop.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "회원가입 시 유저이름은 필수 입니다")
    private String username;

    @NotBlank(message = "회원가입 시 패스워드는 필수 입니다")
    private String password;

    @NotBlank(message = "회원가입 시 이메일은 필수 입니다")
    private String email;

    private boolean admin = false;

    private String adminToken = "";
}
