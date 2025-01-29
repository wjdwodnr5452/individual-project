package com.individual.individual_project.domain.board.dto;

import lombok.Data;

@Data
public class ServiceBoardSearchDto {

    private Long serviceStatId;
    private Long recruitStatId;
    private Long categoryId;

    public ServiceBoardSearchDto() {
    }

    public ServiceBoardSearchDto(Long serviceStatId, Long recruitStatId, Long categoryId) {
        this.serviceStatId = serviceStatId;
        this.recruitStatId = recruitStatId;
        this.categoryId = categoryId;
    }
}
