package com.individual.individual_project.domain.applicant.controller;

import com.individual.individual_project.domain.applicant.Applicant;
import com.individual.individual_project.domain.applicant.service.ApplicantService;
import com.individual.individual_project.domain.response.ApiResponse;
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



        applicantService.save(id, request);

        return null;
    }


}
