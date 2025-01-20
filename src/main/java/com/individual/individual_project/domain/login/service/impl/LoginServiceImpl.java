package com.individual.individual_project.domain.login.service.impl;

import com.individual.individual_project.comm.Encrypt;
import com.individual.individual_project.domain.exception.BaseException;
import com.individual.individual_project.domain.login.dto.LoginDto;
import com.individual.individual_project.domain.login.repository.LoginRepository;
import com.individual.individual_project.domain.login.service.LoginService;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final Encrypt encrypt;

    @Override
    public User login(LoginDto loginDto) {

        User user = loginRepository.findByLoginId(loginDto)
                .orElseThrow(() -> new BaseException(ResponseCode.USER_NOT_FOUND_EMAIL));

        String encryptedPassword = encrypt.encryptSha256(loginDto.getPassword());

        if(!user.getPassword().equals(encryptedPassword)){ // 비밀번호 일치
            throw new BaseException(ResponseCode.USER_NOT_FOUND_PASSWORD);
        }

        return user;
    }
}
