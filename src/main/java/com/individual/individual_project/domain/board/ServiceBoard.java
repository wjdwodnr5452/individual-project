package com.individual.individual_project.domain.board;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service_boards")
public class ServiceBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_title")
    private String serviceTitle;

    @Column(name = "recruitCount")
    private int recruitCount;



}
