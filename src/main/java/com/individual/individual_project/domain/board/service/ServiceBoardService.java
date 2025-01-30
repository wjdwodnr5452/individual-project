package com.individual.individual_project.domain.board.service;

import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.dto.ServiceBoardResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceBoardService {

   ServiceBoard createServiceBoard(String title, String category, String  content, String recruitCount, String serviceTime, String deadline, String serviceDate, MultipartFile thumbnail, Long userId);

   void updateServiceBoardStat(LocalDateTime currentTime);

   List<ServiceBoardResponseDto> findAll(String serviceStatId, String recruitStatId, String categoryId, String serviceBoardSearchName);

}
