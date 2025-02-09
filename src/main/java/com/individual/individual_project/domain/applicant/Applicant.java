package com.individual.individual_project.domain.applicant;

import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "applicant_date")
    @CreatedDate
    private LocalDate applicantDate;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="service_board_id", referencedColumnName = "id")
    private ServiceBoard serviceBoard;

    @Column(name = "reg_date", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime  updateDate;

    @ManyToOne
    @JoinColumn(name = "applicant_stat_id", referencedColumnName = "id")
    private Status applicantStat;

    public Applicant() {}

    public Applicant(LocalDate applicantDate, User user, ServiceBoard serviceBoard, LocalDateTime regDate, LocalDateTime updateDate, Status applicantStat) {
        this.applicantDate = applicantDate;
        this.user = user;
        this.serviceBoard = serviceBoard;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.applicantStat = applicantStat;
    }
}
