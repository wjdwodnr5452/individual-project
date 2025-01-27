package com.individual.individual_project.domain.board.repository;

import com.individual.individual_project.domain.board.ServiceBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceBoardDataJpa extends JpaRepository<ServiceBoard, Long> {
}
