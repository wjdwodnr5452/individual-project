package com.individual.individual_project.domain.applicant.dto;

import lombok.Data;

@Data
public class ApplicantServiceBordsFindDto {
    Long applicantId;
    Long applicantStatId;
    String applicantStatName;

    public ApplicantServiceBordsFindDto() {}

    public ApplicantServiceBordsFindDto(Long applicantId, Long applicantStatId, String applicantStatName) {
        this.applicantId = applicantId;
        this.applicantStatId = applicantStatId;
        this.applicantStatName = applicantStatName;
    }
}
