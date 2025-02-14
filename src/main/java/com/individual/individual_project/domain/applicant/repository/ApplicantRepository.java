package com.individual.individual_project.domain.applicant.repository;

import com.individual.individual_project.domain.applicant.Applicant;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBordsResponseDto;
import com.individual.individual_project.domain.board.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findByUserIdAndServiceBoardId(Long userId, Long serviceBoardId);
    Long countByServiceBoardIdAndApplicantStatId(Long serviceBoardId, Long applicantStatId);
    List<ApplicantServiceBordsResponseDto> findByServiceBoardId(Long serviceBoardId);

    @Modifying
    @Query("UPDATE Applicant a " +
            "SET a.applicantStat = :updateApplicantStat " +
            "WHERE a.serviceBoard.id IN (" +
            " SELECT s.id FROM ServiceBoard s " +
            " WHERE s.serviceStat = : serviceStatId" +
            ")"+
            "AND a.applicantStat.id = :currentStatId")
    void updateApplicantStat(@Param("updateApplicantStat") Status updateApplicantStat,
                             @Param("updateServiceStat") Status serviceStatId,
                             @Param("currentStatId") Long currentStatId);

}
