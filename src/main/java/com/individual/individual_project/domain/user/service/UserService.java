package com.individual.individual_project.domain.user.service;

import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.dto.UserDetailDto;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {
    UserDetailDto findUserById(Long id);
    User saveUser(User user);
}
