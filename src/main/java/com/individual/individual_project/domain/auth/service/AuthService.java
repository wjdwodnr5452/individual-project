package com.individual.individual_project.domain.auth.service;

import com.individual.individual_project.domain.auth.dto.LoginDto;
import com.individual.individual_project.domain.user.User;

public interface AuthService {
    User login(LoginDto loginDto);
}
