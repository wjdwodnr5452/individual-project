package com.individual.individual_project.domain.user.repository;

import com.individual.individual_project.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    // user 조회
    Optional<User> findUserById(Long id);

    // 이메일 조회
    Optional<User> findUserByEmail(String email);

    // user 회원가입
    User saveUser(User user);
}