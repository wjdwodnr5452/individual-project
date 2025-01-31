package com.individual.individual_project.domain.board.dto;


import lombok.Data;

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



    public ServiceBoardResponseDto() {}

    public ServiceBoardResponseDto(Long id, String serviceTitle, Integer recruitCount, LocalDateTime serviceDate, Integer serviceTime, LocalDateTime deadline, String thumbnailImage, String writer, String categoryName, String serviceContent) {
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
    }

/*    private String decrypt(String encryptedText) {



    }*/
}
