package com.individual.individual_project.domain.board.repository;

import com.individual.individual_project.domain.board.ServiceBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ServiceBoardDataJpa extends JpaRepository<ServiceBoard, Long> {

    @Modifying
    @Query("update ServiceBoard e set e.serviceStatId = 4 where e.serviceDate <= :currentTime and e.serviceStatId = 3")
    void updateServiceStat(LocalDateTime currentTime);

}
