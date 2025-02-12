package com.individual.individual_project.domain.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServiceBoardDetailEditDto {

    private Long id;
    private String serviceTitle;
    private Long cateGoryId;
    private Integer recruitCount;
    private Integer serviceTime;
    private LocalDateTime deadline;
    private LocalDateTime serviceDate;
    private String serviceContent;
    private String thumbnailImage;
    private Long serviceStatId;
    private Long recruitStatId;


    public ServiceBoardDetailEditDto() {}

    public ServiceBoardDetailEditDto(Long id, String serviceTitle, Long cateGoryId, Integer recruitCount, Integer serviceTime, LocalDateTime deadline, LocalDateTime serviceDate, String serviceContent, String thumbnailImage, Long serviceStatId, Long recruitStatId) {
        this.id = id;
        this.serviceTitle = serviceTitle;
        this.cateGoryId = cateGoryId;
        this.recruitCount = recruitCount;
        this.serviceTime = serviceTime;
        this.deadline = deadline;
        this.serviceDate = serviceDate;
        this.serviceContent = serviceContent;
        this.thumbnailImage = thumbnailImage;
        this.serviceStatId = serviceStatId;
        this.recruitStatId = recruitStatId;
    }
}
