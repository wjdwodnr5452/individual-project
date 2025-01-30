package com.individual.individual_project.domain.board;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "service_boards")
public class ServiceBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message ="BORD_NOT_TITLE")
    @Column(name = "service_title")
    private String serviceTitle;

    @NotNull(message ="BORD_NOT_RECRUIT_COUNT")
    @Column(name = "recruit_count")
    private Integer recruitCount;

    @NotNull(message ="BORD_NOT_SERVICE_DATE")
    @Column(name = "service_date")
    private LocalDateTime serviceDate;

    @NotNull(message ="BORD_NOT_SERVICE_TIME")
    @Column(name = "service_time")
    private Integer serviceTime;

    @NotNull(message ="BORD_NOT_DEADLINE")
    private LocalDateTime deadline;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;


    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    @NotNull(message ="BORD_NOT_CATEGORY_ID")
    private Category categoryId;
  //  private Long categoryId;

    @ManyToOne
    @JoinColumn(name="service_stat_id", referencedColumnName = "id")
    private Status serviceStatId;
    // private Long serviceStatId;

    @ManyToOne
    @JoinColumn(name="recruit_stat_id", referencedColumnName = "id")
    private Status recruitStatId;
   // private Long recruitStatId;

    @NotNull(message ="BORD_NOT_SERVICE_CONTENT")
    @Column(name = "service_content")
    private String serviceContent;
    
    public ServiceBoard() {}

    public ServiceBoard(String serviceTitle, Integer recruitCount, LocalDateTime serviceDate, Integer serviceTime, LocalDateTime deadline, String thumbnailImage, Long userId, Category categoryId, Status serviceStatId, Status recruitStatId, String serviceContent) {
        this.serviceTitle = serviceTitle;
        this.recruitCount = recruitCount;
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
        this.deadline = deadline;
        this.thumbnailImage = thumbnailImage;
        this.userId = userId;
        this.categoryId = categoryId;
        this.serviceStatId = serviceStatId;
        this.recruitStatId = recruitStatId;
        this.serviceContent = serviceContent;
    }
}
