package com.individual.individual_project.config;

import com.individual.individual_project.comm.Encrypt;
import com.individual.individual_project.domain.user.repository.SpringDataUserRepository;
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
public class UserBeanConfig {

    private final EntityManager em;
    private final SpringDataUserRepository userRepository;
    private final Encrypt encrypt; // 암호화

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository(),encrypt);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl(em,userRepository);
    }

}
