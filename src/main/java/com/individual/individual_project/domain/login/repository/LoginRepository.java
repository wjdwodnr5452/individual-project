package com.individual.individual_project.domain.login.repository;

import com.individual.individual_project.domain.login.dto.LoginDto;
import com.individual.individual_project.domain.user.User;

import java.util.Optional;

public interface LoginRepository {

    Optional<User> findByLoginId(LoginDto loginDto);

}
