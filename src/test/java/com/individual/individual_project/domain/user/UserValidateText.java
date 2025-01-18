package com.individual.individual_project.domain.user;


import com.individual.individual_project.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringBootTest
class UserValidateText {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlatformTransactionManager transactionManager;

    TransactionStatus status;


    @Test
    void validSaveUser() {
        User user = new User();
        user.setEmail("");
        user.setName("홍길동");
        user.setPhoneNumber("01012341234");
        userRepository.saveUser(user);

    }

}
