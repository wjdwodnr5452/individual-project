package com.individual.individual_project.domain.board.service.impl;

import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.repository.ServiceBoardRepository;
import com.individual.individual_project.domain.board.service.ServiceBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ServiceBoardServiceImpl implements ServiceBoardService {

    private final ServiceBoardRepository serviceBoardRepository;

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public ServiceBoard createServiceBoard(String title, String category, String content, String recruitCount, String serviceTime, String deadline, String serviceDate, MultipartFile thumbnail, Long userId) {


        log.info("thumbnail : {}", thumbnail);
        log.info("thumbnailGetName : {}", thumbnail.getOriginalFilename());
        log.info("fileDir : {}", fileDir);

        ServiceBoard  serviceBoard = new ServiceBoard();
        serviceBoard.setServiceTitle(title);
        serviceBoard.setServiceTime(Integer.valueOf(serviceTime));
        serviceBoard.setRecruitCount(Integer.valueOf(recruitCount));
        serviceBoard.setServiceContent(content);
        serviceBoard.setCategoryId(Long.valueOf(category));
        serviceBoard.setRecruitStatId(1L);
        serviceBoard.setServiceStatId(3L);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        serviceBoard.setServiceDate(LocalDateTime.parse(serviceDate, formatter));
        serviceBoard.setDeadline(LocalDateTime.parse(deadline, formatter));
        serviceBoard.setUserId(userId);

        return serviceBoard;
    }
}
