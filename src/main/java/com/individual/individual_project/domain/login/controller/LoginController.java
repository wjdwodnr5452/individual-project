package com.individual.individual_project.domain.login.controller;

import com.individual.individual_project.SessionConst;
import com.individual.individual_project.domain.login.dto.LoginDto;
import com.individual.individual_project.domain.login.dto.LoginStatusDto;
import com.individual.individual_project.domain.login.service.LoginService;
import com.individual.individual_project.domain.response.ApiResponse;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        log.info("loginDto: {}", loginDto);
        User user = loginService.login(loginDto);
        log.info("user: {}", user);

        // 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, user);

        //return ApiResponse.success(user, ResponseCode.USER_LOGIN_SUCCESS.getMessage());
        return ApiResponse.success(user.getName(), ResponseCode.USER_LOGIN_SUCCESS);
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

       // return ApiResponse.success(null, ResponseCode.USER_LOGOUT_SUCCESS.getMessage());
        return ApiResponse.success(null, ResponseCode.USER_LOGOUT_SUCCESS);
    }


   @GetMapping("/login/status")
    public ApiResponse<String> getLoginStatus(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            return ApiResponse.fail(ResponseCode.USER_LOGOUT_STATUS, null);
        }
       User user = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);

     //  LoginStatusDto loginStatusDto = new LoginStatusDto(user.getId(), user.getName());

       //return ApiResponse.success(user, ResponseCode.USER_LOGIN_STATUS.getMessage());
       return ApiResponse.success(user.getName(), ResponseCode.USER_LOGIN_STATUS);
    }


}
