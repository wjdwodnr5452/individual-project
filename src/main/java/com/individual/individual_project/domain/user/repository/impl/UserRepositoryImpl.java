package com.individual.individual_project.domain.user.repository.impl;

import com.individual.individual_project.domain.user.User;
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


    @Override
    public Optional<User> findUserById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean findUserByEmail(String email) {

        String jpql = "SELECT count(u) FROM User u WHERE u.email = :email";
        Long cnt  =  (Long) em.createQuery(jpql)
                .setParameter("email", email)
                .getSingleResult();

        return cnt > 0 ? true : false;
    }



    @Override
    public User saveUser(User user) {
        em.persist(user);
        return user;
    }


}
