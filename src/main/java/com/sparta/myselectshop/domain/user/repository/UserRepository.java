package com.sparta.myselectshop.domain.user.repository;

import com.sparta.myselectshop.domain.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameOrEmail(String username, String email);
}
