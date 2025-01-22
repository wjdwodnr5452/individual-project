package com.individual.individual_project.domain.board;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 작성자 : 정재욱
 * 생성일 : 2025.01.22
 * 엔티티 : service_stat
 * 서비스 상태
 */
@Data
@Entity
@Table(name = "service_stat")
public class ServiceStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "service_stat_name")
    private String serviceStatName;

    public ServiceStat() {
    }

    public ServiceStat(String serviceStatName) {
        this.serviceStatName = serviceStatName;
    }
}
