package com.individual.individual_project.domain.applicant.controller;

import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBoardsDto;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBordsResponseDto;
import com.individual.individual_project.domain.applicant.dto.ApplicantUserDto;
import com.individual.individual_project.domain.applicant.service.ApplicantService;
import com.individual.individual_project.domain.response.ApiResponse;
import com.individual.individual_project.domain.response.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 신청자
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApplicantController {

    private final ApplicantService applicantService;

    // 봉사 신청
    @PostMapping("/service-boards/{serviceBoardId}/applicants")
    public ApiResponse<ApplicantServiceBordsResponseDto> createApplicant(@PathVariable Long serviceBoardId, HttpServletRequest request) {

        ApplicantServiceBordsResponseDto applicant = applicantService.save(serviceBoardId, request);

        log.info("봉사 신청 성공 : {} ", applicant);

        return ApiResponse.success(applicant, ResponseCode.APPLICANT_CREATE_SUCCESS);
    }

    // 봉사 신청 리스트 조회
    @GetMapping("/service-boards/{serviceBoardId}/applicants")
    public ApiResponse<List<ApplicantServiceBoardsDto>> findApplicants(@PathVariable Long serviceBoardId) {

        List<ApplicantServiceBoardsDto> byServiceBoardId = applicantService.findByServiceBoardId(serviceBoardId);

        log.info("봉사 신청 리스트 조회 성공 : {}", byServiceBoardId);

        return ApiResponse.success(byServiceBoardId, ResponseCode.APPLICANT_READ_SUCCESS);
    }

    // 봉사 신청 상태 변경
    @PatchMapping("/applicants/{id}/status")
    public ApiResponse<ApplicantServiceBordsResponseDto> updateApplicantStat(@PathVariable Long id, @RequestBody Long statusId){

        ApplicantServiceBordsResponseDto applicant = applicantService.updateApplicantStat(id, statusId);

        return ApiResponse.success(applicant, ResponseCode.APPLICANT_UPDATE_SUCCESS);
    }

    // 봉사 신청 조회
    @GetMapping("/applicants/{userId}/{serviceBoardId}")
    public ApiResponse<ApplicantServiceBordsResponseDto> findApplicant(@PathVariable Long userId, @PathVariable Long serviceBoardId, HttpServletRequest request) {

        ApplicantServiceBordsResponseDto applicant= applicantService.findApplicant(userId, serviceBoardId);

        log.info("봉사 신청 조회 성공 : {}", applicant);

        return ApiResponse.success(applicant, ResponseCode.APPLICANT_READ_SUCCESS);
    }


    // 유저 봉사 조회
    @GetMapping("/users/{userId}/applicants")
    public ApiResponse<List<ApplicantUserDto>> findUserApplicants(@PathVariable Long userId, HttpServletRequest request) {

        List<ApplicantUserDto> applicantUser = applicantService.findByUsers(userId, request);

        return ApiResponse.success(applicantUser, ResponseCode.APPLICANT_READ_SUCCESS);
    }



}



