package com.individual.individual_project.domain.user.service.impl;

import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.domain.user.dto.UserDetailDto;
import com.individual.individual_project.domain.user.repository.UserRepository;
import com.individual.individual_project.web.exception.BaseException;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EncryptionService encrypt;


    @Override
    public UserDetailDto findUserById(Long id)    {

        UserDetailDto userDetail = userRepository.findUserDetail(id).orElseThrow(() -> new BaseException(ResponseCode.USER_NOT_FOUND));

        userDetail.setName(encrypt.decryptAes(userDetail.getName()));
        userDetail.setPhoneNumber(encrypt.decryptAes(userDetail.getPhoneNumber()));

        return userDetail;
    }

    @Transactional
    @Override
    public User saveUser(User user) {


        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());

        if(userByEmail.isPresent()){
            throw new BaseException(ResponseCode.USER_CONFLICT_EMAIL);
        }

  /*      Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new BaseException(ResponseCode.USER_CONFLICT_EMAIL);
        }*/

        // 비밀번호 암호화 단방향
        user.setPassword(encrypt.encryptSha256(user.getPassword()));

        // 유저 정보 양방향
        user.setName(encrypt.encryptAes(user.getName()));
        user.setPhoneNumber(encrypt.encryptAes(user.getPhoneNumber()));

/*        try {
            // 유저 정보 양방향
            user.setName(encrypt.encryptAes(user.getName(), encryptionKey));
            user.setPhoneNumber(encrypt.encryptAes(user.getPhoneNumber(), encryptionKey));
        } catch (Exception e) {
            log.info("exception : {} ", e);
            throw new BaseException(ResponseCode.BAD_REQUEST);
        }*/


        return userRepository.save(user);
    }
}
