package com.individual.individual_project.domain.applicant.repository;

import com.individual.individual_project.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
