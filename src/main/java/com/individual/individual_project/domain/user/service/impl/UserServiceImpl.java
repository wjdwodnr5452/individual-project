package com.individual.individual_project.domain.user.service.impl;

import com.individual.individual_project.comm.Encrypt;
import com.individual.individual_project.domain.exception.BaseException;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepository;
import com.individual.individual_project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Encrypt encrypt;

    @Value("${encryption.key}")
    private String encryptionKey;

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User saveUser(User user) {


        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new BaseException(ResponseCode.USER_CONFLICT_EMAIL);
        }

        // 비밀번호 암호화 단방향
        user.setPassword(encrypt.encryptSha256(user.getPassword()));


        try {
            // 유저 정보 양방향
            user.setName(encrypt.encryptAes(user.getName(), encryptionKey));
            user.setPhoneNumber(encrypt.encryptAes(user.getPhoneNumber(), encryptionKey));
        } catch (Exception e) {
            log.info("exception : {} ", e);
            throw new BaseException(ResponseCode.BAD_REQUEST);
        }


        return userRepository.saveUser(user);
    }
}
