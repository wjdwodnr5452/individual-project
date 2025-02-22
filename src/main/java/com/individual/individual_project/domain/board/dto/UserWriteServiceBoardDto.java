package com.individual.individual_project.domain.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserWriteServiceBoardDto {

    private Long id;
    private String serviceTitle;
    private LocalDateTime regDate;

    public UserWriteServiceBoardDto() {}

    public UserWriteServiceBoardDto(Long id, String serviceTitle, LocalDateTime regDate) {
        this.id = id;
        this.serviceTitle = serviceTitle;
        this.regDate = regDate;
    }
}
