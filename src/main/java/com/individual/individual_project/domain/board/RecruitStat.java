package com.individual.individual_project.domain.board;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 작성자 : 정재욱
 * 생성일 : 2025.01.22
 * 엔티티 : recruit_stat
 * 모집상태
 */
@Data
@Entity
@Table(name = "recruit_stat")
public class RecruitStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "recruit_stat_name")
    private String recruitStatName;

    public RecruitStat() {}

    public RecruitStat(String recruitStatName) {
        this.recruitStatName = recruitStatName;
    }

}
