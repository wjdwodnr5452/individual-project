package com.individual.individual_project.domain.board.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ServiceBoardResponseDto {

    private Long id;
    private String serviceTitle;
    private Integer recruitCount;
    private LocalDateTime serviceDate;
    private Integer serviceTime;
    private LocalDateTime deadline;
    private String thumbnailImage;
    private String writer;
    private String categoryName;
    private String serviceContent;
    private String serviceStatName;
    private String recruitStatName;
    private LocalDateTime regDate;

    public ServiceBoardResponseDto() {}

    public ServiceBoardResponseDto(Long id, String serviceTitle, Integer recruitCount, LocalDateTime serviceDate, Integer serviceTime, LocalDateTime deadline, String thumbnailImage, String writer, String categoryName, String serviceContent, String serviceStatName, String recruitStatName, LocalDateTime regDate) {
        this.id = id;
        this.serviceTitle = serviceTitle;
        this.recruitCount = recruitCount;
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
        this.deadline = deadline;
        this.thumbnailImage = thumbnailImage;
        this.writer = writer;
        this.categoryName = categoryName;
        this.serviceContent = serviceContent;
        this.serviceStatName = serviceStatName;
        this.recruitStatName = recruitStatName;
        this.regDate = regDate;
    }

}
