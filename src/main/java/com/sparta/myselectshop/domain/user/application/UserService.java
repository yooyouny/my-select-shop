package com.sparta.myselectshop.domain.user.application;

import com.sparta.myselectshop.domain.user.domain.User;
import com.sparta.myselectshop.domain.user.domain.UserRole;
import com.sparta.myselectshop.domain.user.dto.SignupRequestDto;
import com.sparta.myselectshop.domain.user.repository.UserRepository;
import com.sparta.myselectshop.global.common.ErrorCode;
import com.sparta.myselectshop.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${admin-token}")
    private String adminToken;

    public void signUp(SignupRequestDto requestDto){
        userRepository.findByUserNameOrEmail(requestDto.getUsername(), requestDto.getEmail()).ifPresent(it -> {
            throw new CustomException(ErrorCode.DUPLICATED_USER);
        });
        UserRole role = setUserRole(requestDto);

        User newUser = User.of(requestDto, passwordEncoder.encode(requestDto.getPassword()), role);
        userRepository.save(newUser);
    }

    private UserRole setUserRole(SignupRequestDto requestDto) {
        return requestDto.isAdmin()
                ? validateAdminToken(requestDto.getAdminToken())
                : UserRole.USER;
    }

    private UserRole validateAdminToken(String requestToken){
        if(adminToken.equals(requestToken))
            return UserRole.ADMIN;
        else
            throw new CustomException(ErrorCode.INVALID_ADMIN_TOKEN);
    }
}
