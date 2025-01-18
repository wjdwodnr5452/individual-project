package com.individual.individual_project.domain.user.controller;

import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 작성자 : 정재욱
 * 작성일 : 2025.01.15
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable Long id) {

        User user = userService.findUserById(id).get();

        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user){
        try{
            User saveUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("정상적으로 회원가입 했습니다.");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입에 실패 했습니다.");
        }
    }

}
