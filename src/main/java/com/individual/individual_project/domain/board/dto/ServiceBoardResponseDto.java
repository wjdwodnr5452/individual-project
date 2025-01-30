package com.individual.individual_project.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ServiceBoardResponseDto {

    private Long id;
    private String serviceTitle;
    private Integer recruitCount;
    private LocalDateTime serviceDate;
    private Integer serviceTime;
    private LocalDateTime deadline;
    private String thumbnailImage;
    private String username;
    private String categoryName;
    private String serviceContent;
}
