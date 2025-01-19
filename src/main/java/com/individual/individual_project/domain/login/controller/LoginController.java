package com.individual.individual_project.domain.login.controller;

import com.individual.individual_project.domain.login.dto.LoginDto;
import com.individual.individual_project.domain.login.service.LoginService;
import com.individual.individual_project.domain.response.ApiResponse;
import com.individual.individual_project.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Login(로그인, 로그아웃)
 * 로그인시 세션 생성
 * 로그아웃시 세션 종료
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody LoginDto loginDto) {
        log.info("loginDto: {}", loginDto);
        User user = loginService.login(loginDto);
        log.info("user: {}", user);

        return null;
    }


}
