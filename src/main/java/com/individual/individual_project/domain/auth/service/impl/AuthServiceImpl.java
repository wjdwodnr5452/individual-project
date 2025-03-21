package com.individual.individual_project.domain.auth.service.impl;

import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.web.exception.BaseException;
import com.individual.individual_project.domain.auth.dto.LoginDto;
import com.individual.individual_project.domain.auth.repository.AuthRepository;
import com.individual.individual_project.domain.auth.service.AuthService;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final EncryptionService encrypt;

    @Override
    public User login(LoginDto loginDto) {

        User user = authRepository.findByLoginId(loginDto)
                .orElseThrow(() -> new BaseException(ResponseCode.USER_NOT_FOUND_EMAIL));

        String encryptedPassword = encrypt.encryptSha256(loginDto.getPassword());

        if(!user.getPassword().equals(encryptedPassword)){ // 비밀번호 일치
            throw new BaseException(ResponseCode.USER_NOT_FOUND_PASSWORD);
        }

        user.setName(encrypt.decryptAes(user.getName())); // 디코딩
        user.setPhoneNumber(encrypt.decryptAes(user.getPhoneNumber()));


        return user;
    }
}
