package com.individual.individual_project.domain.applicant.dto;

import lombok.Data;

@Data
public class ApplicantUserDto {

    private Long id;
    private String applicantStatusName;
    private String serviceBoardTitle;
    private Integer userServiceTime;

    public ApplicantUserDto() {}

    public ApplicantUserDto(Long id, String applicantStatusName, String serviceBoardTitle, Integer userServiceTime) {
        this.id = id;
        this.applicantStatusName = applicantStatusName;
        this.serviceBoardTitle = serviceBoardTitle;
        this.userServiceTime = userServiceTime;
    }
}
