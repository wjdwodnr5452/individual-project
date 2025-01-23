package com.individual.individual_project.domain.board;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "status_type")
public class StatusType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_type_name")
    private String statusTypeName;

    public StatusType() {}

    public StatusType(Long id, String statusTypeName) {
        this.id = id;
        this.statusTypeName = statusTypeName;
    }

}
