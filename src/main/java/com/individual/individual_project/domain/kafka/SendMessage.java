package com.individual.individual_project.domain.kafka;

public class SendMessage {
    private String userName;
    private String messageTitle;
    private String messageContent;

    public SendMessage(String userName, String messageTitle, String messageContent) {
        this.userName = userName;
        this.messageTitle = messageTitle;
        this.messageContent = messageContent;
    }

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
