package com.individual.individual_project.domain.login;


import com.individual.individual_project.comm.Encrypt;
import com.individual.individual_project.domain.login.dto.LoginDto;
import com.individual.individual_project.domain.login.repository.LoginRepository;
import com.individual.individual_project.domain.login.service.LoginService;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@Slf4j
@SpringBootTest
class LoginTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    Encrypt encrypt;

    @Test
    void login() {

        User user = new User("test@test.com", "Qw123123123!", "홍길동", "01012341234");
        userRepository.saveUser(user);

        LoginDto loginDto = new LoginDto("test@test.com", "Qw123123123!");
        Optional<User> byLoginId = loginRepository.findByLoginId(loginDto);

        // 해당 사용자가 있는지 확인
        Assertions.assertThat(byLoginId.isPresent()).isTrue();
    }

/*    @Test
    void userNotFoundEmail() {

        User user = new User("test@test.com", "Qw123123123!", "홍길동", "01012341234");
        userRepository.saveUser(user);

        LoginDto loginDto = new LoginDto("test1@test.com", "Qw123123123!");
        User login = loginService.login(loginDto);

        ResponseCode userNotFoundEmail = ResponseCode.USER_NOT_FOUND_EMAIL;
        userNotFoundEmail.getMessage();

        log.info("userNotFoundEmail: {}", userNotFoundEmail.getMessage());

        Assertions.assertThat(userNotFoundEmail.getMessage()).isEqualTo("회원가입 한 이메일이 아닙니다.");

    }*/




}
