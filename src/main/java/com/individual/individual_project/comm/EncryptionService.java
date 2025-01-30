package com.individual.individual_project.comm;

import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.web.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 암호화 복호화 모듈화
 */
@Component
@RequiredArgsConstructor
public class EncryptionService {

    private final Encrypt encrypt;

    @Value("${encryption.key}")
    private String encryptionKey;


    public String encryptSha256(String value) {
        String encrypted = encrypt.encryptSha256(value);
        return encrypted;
    }

    public String encryptAes(String str) {

        try {
            String encrypted = encrypt.encryptAes(str, encryptionKey);
            return encrypted;
        } catch (Exception e) {
            throw new BaseException(ResponseCode.BAD_REQUEST);
        }

    }

    public String decryptAes(String str) {

        try {
            String decryptString = encrypt.decryptAes(str, encryptionKey);
            return decryptString;
        } catch (Exception e) {
            throw new BaseException(ResponseCode.BAD_REQUEST);
        }

    }

}
