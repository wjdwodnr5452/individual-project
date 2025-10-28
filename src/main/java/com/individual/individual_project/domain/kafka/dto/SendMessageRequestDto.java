package com.individual.individual_project.domain.kafka.dto;

public class SendMessageRequestDto {

    private String userName;
    private String messageTitle;
    private String messageContent;

    public String getUserName() {
        return userName;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }
}
