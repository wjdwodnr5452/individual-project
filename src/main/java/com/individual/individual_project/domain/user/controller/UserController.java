package com.individual.individual_project.domain.user.controller;

import com.individual.individual_project.domain.response.ApiResponse;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.dto.UserDetailDto;
import com.individual.individual_project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 작성자 : 정재욱
 * url :
 * 1. GET /users/{id} 회원가져오기
 * 2. POST /users 회원등록
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ApiResponse<UserDetailDto> getUsers(@PathVariable String id) {
        Long userId = Long.valueOf(id);
        UserDetailDto user = userService.findUserById(userId);
        return ApiResponse.success(user, ResponseCode.USER_READ_SUCCESS);
    }

    @PostMapping("/users")
    public ApiResponse<User> createUser(@Validated @RequestBody User user){
      //  return ApiResponse.success(userService.saveUser(user), ResponseCode.USER_CREATE_SUCCESS.getMessage());
        return ApiResponse.success(userService.saveUser(user), ResponseCode.USER_CREATE_SUCCESS);
    }


}
