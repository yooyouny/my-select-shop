package com.sparta.myselectshop.domain.user.api;

import com.sparta.myselectshop.domain.user.application.UserService;
import com.sparta.myselectshop.domain.user.domain.UserDetailsImpl;
import com.sparta.myselectshop.domain.user.domain.UserRole;
import com.sparta.myselectshop.domain.user.dto.SignupRequestDto;
import com.sparta.myselectshop.domain.user.dto.UserInfoDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @GetMapping("/user/login-page")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/user/signup")
    public String signupPage(){
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(@Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "redirect:/api/user/signup";
        }

        userService.signUp(signupRequestDto);
        return "redirect:/api/user/login-page";
    }

    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetail){
        boolean isAdmin = (userDetail.getUser().getRole() == UserRole.ADMIN);
        return UserInfoDto.of(userDetail.getUser().getUserName(), isAdmin);
    }
}
