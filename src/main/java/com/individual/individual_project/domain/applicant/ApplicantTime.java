package com.individual.individual_project.domain.applicant;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "applicant_time")
public class ApplicantTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name ="applicant_id", referencedColumnName ="id")
    private Applicant applicant;

    @Column(name = "service_time")
    private Integer serviceTime;

    @Column(name = "reg_date", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime  updateDate;

    public ApplicantTime() {}

    public ApplicantTime(Applicant applicant, Integer serviceTime) {
        this.applicant = applicant;
        this.serviceTime = serviceTime;
    }

    public ApplicantTime(Applicant applicant, Integer serviceTime, LocalDateTime regDate, LocalDateTime updateDate) {
        this.applicant = applicant;
        this.serviceTime = serviceTime;
        this.regDate = regDate;
        this.updateDate = updateDate;
    }
}
