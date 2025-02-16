package com.individual.individual_project.domain.applicant.dto;

import lombok.Data;

@Data
public class SaveApplicantServiceTimeDto {

    private Long applicantId;
    private Integer serviceTime;

    public SaveApplicantServiceTimeDto() {}

    public SaveApplicantServiceTimeDto(Long applicantId, Integer serviceTime) {
        this.applicantId = applicantId;
        this.serviceTime = serviceTime;
    }
}
