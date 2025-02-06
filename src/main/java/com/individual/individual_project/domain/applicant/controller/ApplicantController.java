package com.individual.individual_project.domain.applicant.controller;

import com.individual.individual_project.domain.applicant.Applicant;
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

    @PostMapping("/applicant/{id}")
    public ApiResponse<Applicant> createApplicant(@PathVariable Long id, HttpServletRequest request) {

        Applicant applicant = applicantService.save(id, request);

        log.info("봉사 신청 성공 : {} ", applicant);

        return ApiResponse.success(applicant, ResponseCode.APPLICANT_CREATE_SUCCESS);
    }

    @GetMapping("/applicant/{userId}/{boardId}")
    public ApiResponse<Boolean> getApplicant(@PathVariable Long userId, @PathVariable Long boardId) {

        

        return null;
    }


}
