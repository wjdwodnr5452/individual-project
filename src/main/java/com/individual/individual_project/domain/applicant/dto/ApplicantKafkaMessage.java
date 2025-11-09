package com.individual.individual_project.domain.applicant.dto;

import java.time.LocalDateTime;

public class ApplicantKafkaMessage {

    private Long serviceBoardId;
    private Long userId;
    private Long applicantId;
    private Long serviceBoardWriterId;


    public ApplicantKafkaMessage(Long serviceBoardId, Long userId, Long applicantId, Long serviceBoardWriterId) {
        this.serviceBoardId = serviceBoardId;
        this.userId = userId;
        this.applicantId = applicantId;
        this.serviceBoardWriterId = serviceBoardWriterId;
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

    public Long getServiceBoardWriterId() {
        return serviceBoardWriterId;
    }

}
