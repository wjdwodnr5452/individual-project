package com.individual.individual_project.domain.user.service;

import com.individual.individual_project.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {
    Optional<User> findUserById(Long id);

    User saveUser(User user);
}
