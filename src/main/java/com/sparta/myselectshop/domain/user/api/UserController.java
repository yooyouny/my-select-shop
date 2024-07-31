package com.sparta.myselectshop.domain.user.api;

import com.sparta.myselectshop.domain.user.application.UserService;
import com.sparta.myselectshop.domain.user.dto.SignupRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/login-page")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid SignupRequestDto signupRequestDto){
        userService.signUp(signupRequestDto);
        return "redirect:/api/user/login-page";
    }
}
