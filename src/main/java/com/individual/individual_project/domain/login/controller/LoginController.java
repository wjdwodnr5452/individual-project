package com.individual.individual_project.domain.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Login(로그인, 로그아웃)
 * 로그인시 세션 생성
 * 로그아웃시 세션 종료
 */
@Slf4j
@RestController
public class LoginController {
    @PostMapping("/login")
    public String login() {
        return null;
    }
}
