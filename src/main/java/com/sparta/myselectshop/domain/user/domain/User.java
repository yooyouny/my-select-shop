package com.sparta.myselectshop.domain.user.domain;

import com.sparta.myselectshop.domain.user.dto.SignupRequestDto;
import com.sparta.myselectshop.global.common.DateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "유저이름은 필수값 입니다")
    private String userName;

    @Column
    @NotBlank(message = "패스워드는 필수값 입니다")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "이메일은 필수값 입니다")
    private String email;

    @Column
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "유저롤은 필수값 입니다")
    private UserRole role;

    @Builder
    public User(String userName, String password, String email, UserRole role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public static User of(SignupRequestDto requestDto, String encodedPassword, UserRole role) {
        return User.builder()
                .userName(requestDto.getUsername())
                .password(encodedPassword)
                .email(requestDto.getEmail())
                .role(role)
                .build();
    }
}
