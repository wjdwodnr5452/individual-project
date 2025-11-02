package com.individual.individual_project.domain.applicant.dto;

import java.time.LocalDateTime;

public class ApplicantKafkaMessage {

    private Long serviceBoardId;
    private Long userId;
    private Long applicantId;


    public ApplicantKafkaMessage(Long serviceBoardId, Long userId, Long applicantId) {
        this.serviceBoardId = serviceBoardId;
        this.userId = userId;
        this.applicantId = applicantId;
    }


    public Long getServiceBoardId() {
        return serviceBoardId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getApplicantId() {
        return applicantId;
    }
}
