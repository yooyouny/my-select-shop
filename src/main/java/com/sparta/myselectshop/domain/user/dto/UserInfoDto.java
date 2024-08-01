package com.sparta.myselectshop.domain.user.dto;

public record UserInfoDto(
        String username,
        boolean isAdmin
) {
    public static UserInfoDto of(String username, boolean isAdmin) {
        return new UserInfoDto(username, isAdmin);
    }
}
