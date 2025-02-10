package com.individual.individual_project.domain.applicant.repository;

import com.individual.individual_project.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findByUserIdAndServiceBoardId(Long userId, Long serviceBoardId);
    Long countByServiceBoardIdAndApplicantStatId(Long serviceBoardId, Long applicantStatId);
}
