package com.individual.individual_project.domain.applicant.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApplicantServiceBoardsDto {

    private Long id;
    private String userName;
    private String phoneNumber;
    private LocalDate applicantDate;
    private Long applicantStat;
    private String applicantStatName;


    public ApplicantServiceBoardsDto() {}

    public ApplicantServiceBoardsDto(Long id, String userName, String phoneNumber, LocalDate applicantDate, Long applicantStat, String applicantStatName) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.applicantDate = applicantDate;
        this.applicantStat = applicantStat;
        this.applicantStatName = applicantStatName;
    }
}
