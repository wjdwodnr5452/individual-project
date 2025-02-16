package com.individual.individual_project.domain.applicant.repository;

import com.individual.individual_project.domain.applicant.Applicant;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBoardsDto;
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

    @Query("SELECT new com.individual.individual_project.domain.applicant.dto.ApplicantServiceBoardsDto(" +
            "a.id, u.name, u.phoneNumber, a.applicantDate, a.applicantStat.id, a.applicantStat.statusName) " +
            "FROM Applicant a " +
            "JOIN User u ON u.id = a.user.id " +
            "JOIN Status s ON s.id = a.applicantStat.id " +
            "WHERE a.serviceBoard.id = :serviceBoardId " +
            "AND s.id != 8 ")
    List<ApplicantServiceBoardsDto> findByServiceBoardId(@Param("serviceBoardId") Long serviceBoardId);

    @Modifying
    @Query("UPDATE Applicant a " +
            "SET a.applicantStat = :updateApplicantStat " +
            "WHERE a.serviceBoard.id IN (" +
            " SELECT s.id FROM ServiceBoard s " +
            " WHERE s.serviceStat = :updateServiceStat " +
            ")"+
            "AND a.applicantStat.id = :currentStatId ")
    void updateApplicantStat(@Param("updateApplicantStat") Status updateApplicantStat,
                             @Param("updateServiceStat") Status updateServiceStat,
                             @Param("currentStatId") Long currentStatId);

}
