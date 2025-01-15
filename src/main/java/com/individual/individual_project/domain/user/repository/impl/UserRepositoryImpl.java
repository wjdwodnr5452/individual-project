package com.individual.individual_project.domain.user.repository.impl;

import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager em;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public User saveUser(User user) {
        em.persist(user);
        return user;
    }
}
