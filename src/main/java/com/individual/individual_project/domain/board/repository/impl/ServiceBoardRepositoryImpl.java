package com.individual.individual_project.domain.board.repository.impl;

import com.individual.individual_project.domain.board.repository.ServiceBoardDataJpa;
import com.individual.individual_project.domain.board.repository.ServiceBoardRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ServiceBoardRepositoryImpl implements ServiceBoardRepository {

    private final EntityManager em;
    private final ServiceBoardDataJpa serviceBoardDataJpa;



}
