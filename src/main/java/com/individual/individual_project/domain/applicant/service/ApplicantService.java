package com.individual.individual_project.domain.applicant.service;

import com.individual.individual_project.domain.applicant.Applicant;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBordsFindDto;
import jakarta.servlet.http.HttpServletRequest;

public interface ApplicantService {

    Applicant save(Long id, HttpServletRequest request);

    ApplicantServiceBordsFindDto findApplicant(Long userId, Long serviceBoardId);
}
