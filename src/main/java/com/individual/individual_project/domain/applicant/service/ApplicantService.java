package com.individual.individual_project.domain.applicant.service;

import com.individual.individual_project.domain.applicant.Applicant;
import jakarta.servlet.http.HttpServletRequest;

public interface ApplicantService {

    Applicant save(Long id, HttpServletRequest request);
}
