package com.individual.individual_project.domain.board.comm;

import com.individual.individual_project.domain.board.service.ServiceBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 게시판 서비스 상태, 마감상태 업데이트
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceBoardScheduler {

    private final ServiceBoardService serviceBoardService;

    // 30분마다 실행 (0, 30 분)
    @Scheduled(cron = "0 0/30 * * * *")
    public void updateEvery30Minutes() {
        log.info("30분마다 작업 실행: {}", LocalDateTime.now());
        serviceBoardService.updateServiceBoardStat(LocalDateTime.now());
    }
}
