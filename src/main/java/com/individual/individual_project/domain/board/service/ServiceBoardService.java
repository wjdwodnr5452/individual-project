package com.individual.individual_project.domain.board.service;

import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


public interface ServiceBoardService {

   ServiceBoard createServiceBoard(String title, String category, String  content, String recruitCount, String serviceTime, String deadline, String serviceDate, MultipartFile thumbnail, Long userId);

   void updateServiceBoardStat(LocalDateTime currentTime);

   Page<ServiceBoardsDto> findAll(String serviceStatId, String recruitStatId, String categoryId, String serviceBoardSearchName, Pageable pageable);

   ServiceBoardDetailDto findServiceBoardById(String id, HttpServletRequest request);

   ServiceBoardDetailEditDto findServiceBoardEditById(Long id, HttpServletRequest request);

   ServiceBoardDetailEditDto updateServiceBoardEdit(Long id, String title, String category, String  content, String recruitCount, String serviceTime, String deadline, String serviceDate, MultipartFile thumbnail,Long userId);

   Status saveServiceTimeAndComplete(Long id, List<SaveApplicantServiceTimeDto> saveApplicantServiceTimeDto);

   List<UserWriteServiceBoardDto> findServiceBoardByUserId(Long userId, HttpServletRequest request);
}
