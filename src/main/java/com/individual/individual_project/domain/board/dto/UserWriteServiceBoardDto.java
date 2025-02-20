package com.individual.individual_project.domain.board.dto;

import lombok.Data;

@Data
public class UserWriteServiceBoardDto {

    private Long id;
    private String serviceTitle;

    public UserWriteServiceBoardDto() {}

    public UserWriteServiceBoardDto(Long id, String serviceTitle) {
        this.id = id;
        this.serviceTitle = serviceTitle;
    }
}
