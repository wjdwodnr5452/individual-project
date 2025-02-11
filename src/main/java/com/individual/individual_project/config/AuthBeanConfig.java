package com.individual.individual_project.config;

import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.domain.auth.repository.AuthRepository;
import com.individual.individual_project.domain.auth.repository.impl.AuthRepositoryImpl;
import com.individual.individual_project.domain.auth.service.AuthService;
import com.individual.individual_project.domain.auth.service.impl.AuthServiceImpl;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthBeanConfig {

    private final EntityManager em;
    private final EncryptionService encrypt;

    @Bean
    public AuthService loginService() {
        return new AuthServiceImpl(authRepository(), encrypt);
    }

    @Bean
    public AuthRepository authRepository() {
        return new AuthRepositoryImpl(em);
    }


}
