package com.individual.individual_project.domain.login.service;

import com.individual.individual_project.domain.login.dto.LoginDto;
import com.individual.individual_project.domain.user.User;

public interface LoginService {
    User login(LoginDto loginDto);
}
