package com.individual.individual_project.domain.board.repository;

import com.individual.individual_project.domain.board.ServiceBoard;

import java.time.LocalDateTime;

public interface ServiceBoardRepository{

    ServiceBoard save(ServiceBoard serviceBoard);

    void updateServiceStat(LocalDateTime currentTime);
}
