package com.individual.individual_project.domain.auth.repository.impl;

import com.individual.individual_project.domain.auth.dto.LoginDto;
import com.individual.individual_project.domain.auth.repository.AuthRepository;
import com.individual.individual_project.domain.user.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {

    private final EntityManager em;

    @Override
    public Optional<User> findByLoginId(LoginDto loginDto) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        List<User> users = em.createQuery(jpql, User.class)
                .setParameter("email", loginDto.getEmail())
                .getResultList();

        // 첫 번째 결과를 반환하거나 Optional.empty() 반환
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

}
