package com.sparta.myselectshop.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "관심상품을 찾을 수 없습니다"),
    DUPLICATED_USER(HttpStatus.CONFLICT, "이미 가입된 유저입니다"),
    INVALID_ADMIN_TOKEN(HttpStatus.UNAUTHORIZED, "관리자 토큰이 유효하지 않습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러")
    ;
    private final HttpStatus status;
    private final String message;
}
