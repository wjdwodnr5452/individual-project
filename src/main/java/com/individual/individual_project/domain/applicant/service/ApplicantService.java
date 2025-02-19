package com.individual.individual_project.domain.applicant.service;

import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBoardsDto;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBordsResponseDto;
import com.individual.individual_project.domain.applicant.dto.ApplicantUserDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ApplicantService {

    ApplicantServiceBordsResponseDto save(Long serviceBoardId, HttpServletRequest request);

    ApplicantServiceBordsResponseDto findApplicant(Long userId, Long serviceBoardId);

    ApplicantServiceBordsResponseDto updateApplicantStat(Long id, Long statusId);

    List<ApplicantServiceBoardsDto> findByServiceBoardId(Long serviceBoardId);

    List<ApplicantUserDto> findByUsers(Long id, HttpServletRequest request);

}
