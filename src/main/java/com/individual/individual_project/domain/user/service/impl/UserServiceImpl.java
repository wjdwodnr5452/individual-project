package com.individual.individual_project.domain.user.service.impl;

import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepository;
import com.individual.individual_project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }
}
