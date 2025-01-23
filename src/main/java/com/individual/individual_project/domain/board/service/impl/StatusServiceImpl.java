package com.individual.individual_project.domain.board.service.impl;

import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.repository.StatusRepository;
import com.individual.individual_project.domain.board.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public List<Status> findByStatusTypeId(Long statTypId) {
        List<Status> statServiceList = statusRepository.findByStatusTypeId(statTypId);
        log.info("statServiceList : {} ", statServiceList);
        return statServiceList;
    }
}
