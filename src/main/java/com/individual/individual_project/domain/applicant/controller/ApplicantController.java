package com.individual.individual_project.domain.applicant.controller;

import com.individual.individual_project.domain.applicant.Applicant;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBordsResponseDto;
import com.individual.individual_project.domain.applicant.service.ApplicantService;
import com.individual.individual_project.domain.response.ApiResponse;
import com.individual.individual_project.domain.response.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 신청자
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping("/applicants/{serviceBoardId}")
    public ApiResponse<ApplicantServiceBordsResponseDto> createApplicant(@PathVariable Long serviceBoardId, HttpServletRequest request) {

        ApplicantServiceBordsResponseDto applicant = applicantService.save(serviceBoardId, request);

        log.info("봉사 신청 성공 : {} ", applicant);

        return ApiResponse.success(applicant, ResponseCode.APPLICANT_CREATE_SUCCESS);
    }


    @PutMapping("/applicants/{id}")
    public ApiResponse<ApplicantServiceBordsResponseDto> updateApplicantStat(@PathVariable Long id, @RequestBody Long statusId){

        ApplicantServiceBordsResponseDto applicant = applicantService.updateApplicantStat(id, statusId);

        return ApiResponse.success(applicant, ResponseCode.APPLICANT_UPDATE_SUCCESS);
    }

    @GetMapping("/applicants/{userId}/{serviceBoardId}")
    public ApiResponse<ApplicantServiceBordsResponseDto> findApplicant(@PathVariable Long userId, @PathVariable Long serviceBoardId, HttpServletRequest request) {

        ApplicantServiceBordsResponseDto applicant= applicantService.findApplicant(userId, serviceBoardId);

        log.info("봉사 신청 조회 성공 : {}", applicant);

        return ApiResponse.success(applicant, ResponseCode.APPLICANT_READ_SUCCESS);
    }


}



