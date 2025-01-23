package com.individual.individual_project.domain.board.service;

import com.individual.individual_project.domain.board.Status;

import java.util.List;

public interface StatusService {

    List<Status> findByStatusTypeId(Long statTypId);

}
