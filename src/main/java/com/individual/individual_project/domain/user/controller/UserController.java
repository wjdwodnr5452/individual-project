package com.individual.individual_project.domain.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User(회원정보)
 */
@Slf4j
@RestController
public class UserController {

    @GetMapping("/user")
    public String user() {
        return "user";
    }
}
