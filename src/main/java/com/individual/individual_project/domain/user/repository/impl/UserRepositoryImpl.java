package com.individual.individual_project.domain.user.repository.impl;

import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepositorySpringData;
import com.individual.individual_project.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager em;
    private final UserRepositorySpringData repository;

    @Override
    public Optional<User> findUserById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

}
