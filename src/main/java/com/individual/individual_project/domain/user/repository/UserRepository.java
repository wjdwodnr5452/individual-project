package com.individual.individual_project.domain.user.repository;

import com.individual.individual_project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {

    // user 조회
    Optional<User> findUserById(Long id);

    // 이메일 중복확인
    boolean findUserByEmail(String email);

    // user 회원가입
    User saveUser(User user);
}