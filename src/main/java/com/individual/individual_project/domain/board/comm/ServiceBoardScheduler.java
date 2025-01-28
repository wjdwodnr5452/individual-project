package com.individual.individual_project.domain.board.comm;

import com.individual.individual_project.domain.board.service.ServiceBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class ServiceBoardScheduler {

    private final ServiceBoardService serviceBoardService;

    // 30분마다 실행 (0, 30 분)
    @Scheduled(cron = "0 0/30 * * * *")
    public void updateEvery30Minutes() {
        log.info("30분마다 작업 실행: {}", LocalDateTime.now());
        serviceBoardService.updateServiceBoardStat(LocalDateTime.now());
    }
}
