package com.individual.individual_project.config;

import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.domain.login.repository.LoginRepository;
import com.individual.individual_project.domain.login.repository.impl.LoginRepositoryImpl;
import com.individual.individual_project.domain.login.service.LoginService;
import com.individual.individual_project.domain.login.service.impl.LoginServiceImpl;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoginBeanConfig {

    private final EntityManager em;
    private final EncryptionService encrypt;

    @Bean
    public LoginService loginService() {
        return new LoginServiceImpl(loginRepository(), encrypt);
    }

    @Bean
    public LoginRepository loginRepository() {
        return new LoginRepositoryImpl(em);
    }


}
