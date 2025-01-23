package com.individual.individual_project.domain.board.repository;

import com.individual.individual_project.domain.board.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {

    List<Status> findByStatusTypeId(Long id);

}
