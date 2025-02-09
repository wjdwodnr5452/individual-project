package com.individual.individual_project.domain.board.repository;

import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ServiceBoardDataJpa extends JpaRepository<ServiceBoard, Long> {

 /*   @Modifying
    @Query("update ServiceBoard e set e.serviceStatId = 4 where e.serviceDate <= :currentTime and e.serviceStatId = 3")
    void updateServiceStat(LocalDateTime currentTime);*/

    @Modifying
    @Query("UPDATE ServiceBoard e SET e.serviceStat = :updateServiceStat WHERE e.serviceDate <= :currentTime AND e.serviceStat.id = :currentStatusId")
    void updateServiceStat(@Param("currentTime") LocalDateTime currentTime,
                           @Param("updateServiceStat") Status updateServiceStat,
                           @Param("currentStatusId") Long currentStatusId);


    @Modifying
    @Query("UPDATE ServiceBoard e SET e.recruitStat = :updateRecruitStat WHERE e.serviceDate <= :currentTime AND e.serviceStat.id = :currentStatusId")
    void updateRecruitStatId(@Param("currentTime") LocalDateTime currentTime,
                           @Param("updateRecruitStat") Status updateRecruitStat,
                           @Param("currentStatusId") Long currentStatusId);






  /*  @Modifying
    @Query("update ServiceBoard e set e.recruitStatId = 2 where e.deadline <= :currentTime and e.recruitStatId = 1")
    void updateRecruitStatId(LocalDateTime currentTime);*/

}
