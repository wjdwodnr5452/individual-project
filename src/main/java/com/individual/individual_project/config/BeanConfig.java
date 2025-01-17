package com.individual.individual_project.config;

import com.individual.individual_project.domain.user.repository.UserRepository;
import com.individual.individual_project.domain.user.repository.impl.UserRepositoryImpl;
import com.individual.individual_project.domain.user.service.UserService;
import com.individual.individual_project.domain.user.service.impl.UserServiceImpl;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final EntityManager em;

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(em);
    }

}
