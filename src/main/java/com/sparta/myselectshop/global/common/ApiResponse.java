package com.sparta.myselectshop.global.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;

    public ApiResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(HttpStatus status, String message, T data){
        return new ApiResponse<>(status, message, data);
    }
    public static <T> ApiResponse<T> ok(HttpStatus status, String message){
        return new ApiResponse<>(status, message, null);
    }
    public static <T> ApiResponse<T> error(ErrorCode code){
        return new ApiResponse<>(code.getStatus(), code.getMessage(), null);
    }
    public static <T> ApiResponse<T> error(HttpStatus status, String message){
        return new ApiResponse<>(status, message, null);
    }


}
