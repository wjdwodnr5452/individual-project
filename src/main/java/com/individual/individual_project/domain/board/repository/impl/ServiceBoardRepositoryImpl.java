package com.individual.individual_project.domain.board.repository.impl;

import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.repository.ServiceBoardDataJpa;
import com.individual.individual_project.domain.board.repository.ServiceBoardRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ServiceBoardRepositoryImpl implements ServiceBoardRepository {

    private final EntityManager em;
    private final ServiceBoardDataJpa serviceBoardDataJpa;


    @Override
    public ServiceBoard save(ServiceBoard serviceBoard) {
        ServiceBoard save = serviceBoardDataJpa.save(serviceBoard);

        log.info("save : {} ", save);
        return save;
    }

    @Override
    public void updateServiceStat(LocalDateTime currentTime) {
        serviceBoardDataJpa.updateServiceStat(currentTime);
    }

    @Override
    public void updateRecruitStatId(LocalDateTime currentTime) {
        serviceBoardDataJpa.updateRecruitStatId(currentTime);
    }

}
