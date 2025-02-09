package com.individual.individual_project.domain.applicant.dto;

import lombok.Data;

@Data
public class ApplicantServiceBordsResponseDto {
    Long applicantId;
    Long applicantStatId;
    String applicantStatName;

    public ApplicantServiceBordsResponseDto() {}

    public ApplicantServiceBordsResponseDto(Long applicantId, Long applicantStatId, String applicantStatName) {
        this.applicantId = applicantId;
        this.applicantStatId = applicantStatId;
        this.applicantStatName = applicantStatName;
    }
}
