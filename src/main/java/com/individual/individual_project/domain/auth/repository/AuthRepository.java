package com.individual.individual_project.domain.auth.repository;

import com.individual.individual_project.domain.auth.dto.LoginDto;
import com.individual.individual_project.domain.user.User;

import java.util.Optional;

public interface AuthRepository {

    Optional<User> findByLoginId(LoginDto loginDto);

}
