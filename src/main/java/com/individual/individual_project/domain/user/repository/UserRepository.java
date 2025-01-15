package com.individual.individual_project.domain.user.repository;

import com.individual.individual_project.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserById(Long id);

    User saveUser(User user);
}
