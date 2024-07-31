package com.sparta.myselectshop.domain.user.application;

import com.sparta.myselectshop.domain.user.domain.User;
import com.sparta.myselectshop.domain.user.domain.UserDetailsImpl;
import com.sparta.myselectshop.domain.user.repository.UserRepository;
import com.sparta.myselectshop.global.common.ErrorCode;
import com.sparta.myselectshop.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_USER));
        return new UserDetailsImpl(user);
    }
}
