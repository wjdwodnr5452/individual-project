package com.individual.individual_project.domain.login.repository.impl;

import com.individual.individual_project.domain.login.dto.LoginDto;
import com.individual.individual_project.domain.login.repository.LoginRepository;
import com.individual.individual_project.domain.user.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository {

    private final EntityManager em;

    @Override
    public Optional<User> findByLoginId(LoginDto loginDto) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        User user = em.createQuery(jpql, User.class)
                .setParameter("email", loginDto.getEmail())
                .getSingleResult();

        return Optional.ofNullable(user);
    }


}
