package com.individual.individual_project.domain.board;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="status_type_id", referencedColumnName = "id")
    private StatusType statusType;

    private String statusName;

    public Status() {}

    public Status(StatusType statusType, String statusName) {
        this.statusType = statusType;
        this.statusName = statusName;
    }



}
