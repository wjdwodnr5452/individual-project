package com.individual.individual_project.domain.board.repository;

import com.individual.individual_project.domain.board.service.ThumbnailImge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbnailImageRepository extends JpaRepository<ThumbnailImge, Long> {
}
