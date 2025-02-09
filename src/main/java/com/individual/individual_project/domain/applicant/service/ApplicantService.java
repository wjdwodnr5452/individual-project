package com.individual.individual_project.domain.applicant.service;

import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBordsResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface ApplicantService {

    ApplicantServiceBordsResponseDto save(Long serviceBoardId, HttpServletRequest request);

    ApplicantServiceBordsResponseDto findApplicant(Long userId, Long serviceBoardId);

    ApplicantServiceBordsResponseDto updateApplicantStat(Long id, Long statusId);
}
