package com.individual.individual_project.domain.user;

import com.individual.individual_project.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlatformTransactionManager transactionManager;

    TransactionStatus status;

    @Test
    void saveUser() {
        User user = new User("test@test.com", "123456", "홍길동", "01012341234");
        userRepository.save(user);
        User findUser = userRepository.findById(user.getId()).get();
        assertThat(findUser).isEqualTo(user);
    }

    @Test
    void findUserByEmail() {
        User user = new User("test@test.com", "Snsteset123!", "홍길동", "01012341234");
        userRepository.save(user);
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        assertThat(userByEmail.isPresent()).isTrue(); // 값이 있는지 존재 확인
        assertThat(userByEmail.get().getEmail()).isEqualTo(user.getEmail()); // 값이 동일하는지 비교
    }




}
