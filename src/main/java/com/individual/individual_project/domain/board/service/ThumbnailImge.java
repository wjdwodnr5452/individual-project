package com.individual.individual_project.domain.board.service;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "thumbnail_images")
public class ThumbnailImge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_filename")
    private String originalFilename;

    @Column(name = "stored_filename")
    private String storedFilename;

    public ThumbnailImge() {}

    public ThumbnailImge(String originalFilename, String storedFilename) {
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
    }


}
