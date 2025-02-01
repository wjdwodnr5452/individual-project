package com.individual.individual_project.domain.board;

import com.individual.individual_project.domain.board.service.ThumbnailImge;
import com.individual.individual_project.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @OneToOne
    @JoinColumn(name = "thumbnail_image_id",  referencedColumnName = "id")
    private ThumbnailImge thumbnailImage;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;


    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id")
    @NotNull(message ="BORD_NOT_CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name="service_stat_id", referencedColumnName = "id")
    private Status serviceStat;

    @ManyToOne
    @JoinColumn(name="recruit_stat_id", referencedColumnName = "id")
    private Status recruitStat;


    @NotNull(message ="BORD_NOT_SERVICE_CONTENT")
    @Column(name = "service_content")
    private String serviceContent;

    @Column(name = "reg_date", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime  regDate;

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime  updateDate;
    
    public ServiceBoard() {}

    public ServiceBoard(String serviceTitle, Integer recruitCount, LocalDateTime serviceDate, Integer serviceTime, LocalDateTime deadline, ThumbnailImge thumbnailImage, User user, Category category, Status serviceStat, Status recruitStat, String serviceContent) {
        this.serviceTitle = serviceTitle;
        this.recruitCount = recruitCount;
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
        this.deadline = deadline;
        this.thumbnailImage = thumbnailImage;
        this.user = user;
        this.category = category;
        this.serviceStat = serviceStat;
        this.recruitStat = recruitStat;
        this.serviceContent = serviceContent;
    }

    public ServiceBoard(String serviceTitle, Integer recruitCount, LocalDateTime serviceDate, Integer serviceTime, LocalDateTime deadline, ThumbnailImge thumbnailImage, User user, Category category, Status serviceStat, Status recruitStat, String serviceContent, LocalDateTime regDate, LocalDateTime updateDate) {
        this.serviceTitle = serviceTitle;
        this.recruitCount = recruitCount;
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
        this.deadline = deadline;
        this.thumbnailImage = thumbnailImage;
        this.user = user;
        this.category = category;
        this.serviceStat = serviceStat;
        this.recruitStat = recruitStat;
        this.serviceContent = serviceContent;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }
}
