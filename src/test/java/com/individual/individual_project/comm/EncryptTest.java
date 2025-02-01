package com.individual.individual_project.comm;

import com.individual.individual_project.comm.encrypt.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class EncryptTest {

    Encrypt encrypt = new Encrypt();

    @Test
    void encryptSha256() {

        /* 단방향 테스트 */
        String password = "1234";
    
        // 단방향 테스트
        log.info("password: {}", password);
        log.info("encryptString : {} ",  encrypt.encryptSha256(password));
        Assertions.assertThat(encrypt.encryptSha256(password)).isLessThan(password);
    }

    @Test
    void encryptAes() throws Exception {

        /* 양방향 테스트 */
        String key16 = "ABCdefg123456plm";
        String email = "test@test.com";

        // 양방향 테스트
        String encryptAesStr = encrypt.encryptAes(email, key16);
        log.info("encryptAesStr: {}", encryptAesStr);

    }

    @Test
    void decryptAes() throws Exception {
        /* 양방향 테스트 */
        String key16 = "ABCdefg123456plm";
        String encryptAesStr = "caD85smlS5x1IownBPnoHA==";

        // 양방향 테스트
        String email = encrypt.decryptAes(encryptAesStr, key16);
        log.info("email: {}", email);
    }

}
