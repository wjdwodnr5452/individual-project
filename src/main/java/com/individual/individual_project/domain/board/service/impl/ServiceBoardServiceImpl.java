package com.individual.individual_project.domain.board.service.impl;

import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.comm.file.FileUploadService;
import com.individual.individual_project.comm.file.UploadFileDto;
import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.dto.ServiceBoardResponseDto;
import com.individual.individual_project.domain.board.repository.*;
import com.individual.individual_project.domain.board.service.ServiceBoardService;
import com.individual.individual_project.domain.board.service.ThumbnailImge;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepositorySpringData;
import com.individual.individual_project.web.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ServiceBoardServiceImpl implements ServiceBoardService {

    private final ServiceBoardRepository serviceBoardRepository;
    private final ServiceBoardDataJpa serviceBoardDataJpa;
    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;
    private final UserRepositorySpringData userRepository;

    private final EncryptionService encryptionService;
    private final FileUploadService fileUploadService;

    private final ThumbnailImageRepository thumbnailImageRepository;

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public ServiceBoard createServiceBoard(String title, String category, String content, String recruitCount, String serviceTime, String deadline, String serviceDate, MultipartFile thumbnail, Long userId) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        Category categoryEntity = categoryRepository.findById(Long.valueOf(category)).orElseThrow(() -> new BaseException(ResponseCode.CATEGORY_NOT_FOUND));
        Status recruitStatEntity = statusRepository.findById(1L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        Status serviceStatEntity = statusRepository.findById(3L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(ResponseCode.USER_NOT_FOUND));

        ThumbnailImge thumbnailImgeSave = null;

        if(thumbnail != null && !thumbnail.isEmpty()){

            UploadFileDto uploadFileDto = fileUploadService.storeFile(thumbnail);

            ThumbnailImge thumbnailImge = new ThumbnailImge();
            thumbnailImge.setStoredFilename(uploadFileDto.getStoreFileName());
            thumbnailImge.setOriginalFilename(uploadFileDto.getUploadFileName());

            thumbnailImgeSave = thumbnailImageRepository.save(thumbnailImge);

        }

        ServiceBoard serviceBoard = new ServiceBoard(title, Integer.valueOf(recruitCount),
                LocalDateTime.parse(serviceDate, formatter),Integer.valueOf(serviceTime),
                LocalDateTime.parse(deadline, formatter), thumbnailImgeSave,
                user, categoryEntity, serviceStatEntity, recruitStatEntity,  content);
        ServiceBoard save = serviceBoardDataJpa.save(serviceBoard);

        return save;
    }

    @Override
    public void updateServiceBoardStat(LocalDateTime currentTime) {

        Status updateRecruitStat = statusRepository.findById(2L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        serviceBoardDataJpa.updateRecruitStatId(currentTime, updateRecruitStat, 1L);

        Status updateServiceStat = statusRepository.findById(4L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        serviceBoardDataJpa.updateServiceStat(currentTime, updateServiceStat, 3L);

    }

    @Override
    public Page<ServiceBoardResponseDto> findAll(String serviceStatId, String recruitStatId, String categoryId, String serviceBoardSearchName, Pageable pageable) {

        Long serviceStatIdLong = (serviceStatId != null) ? Long.valueOf(serviceStatId) : null;
        Long recruitStatIdLong = (recruitStatId != null) ? Long.valueOf(recruitStatId) : null;
        Long categoryIdLong = (categoryId != null) ? Long.valueOf(categoryId) : null;

        // Repository 호출 (페이징 쿼리 적용)
        Page<ServiceBoard> serviceBoardPage = serviceBoardRepository.findAll(
                serviceStatIdLong, recruitStatIdLong, categoryIdLong, serviceBoardSearchName, pageable
        );

        // DTO 변환 및 복호화 처리
        return serviceBoardPage.map(serviceBoard -> {
            String decryptedUserName = encryptionService.decryptAes(serviceBoard.getUser().getName());
            String thumbnailImgPath = (serviceBoard.getThumbnailImage() != null) ? "/api/images/" + serviceBoard.getThumbnailImage().getStoredFilename() : null;

            return new ServiceBoardResponseDto(
                    serviceBoard.getId(),
                    serviceBoard.getServiceTitle(),
                    serviceBoard.getRecruitCount(),
                    serviceBoard.getServiceDate(),
                    serviceBoard.getServiceTime(),
                    serviceBoard.getDeadline(),
                    thumbnailImgPath,
                    decryptedUserName,
                    serviceBoard.getCategory().getCategoryName(),
                    serviceBoard.getServiceContent(),
                    serviceBoard.getServiceStat().getStatusName(),
                    serviceBoard.getRecruitStat().getStatusName(),
                    serviceBoard.getRegDate()
            );
        });
    }

    @Override
    public ServiceBoardResponseDto findServiceBoardById(String id) {

        Long longId = (id != null) ? Long.valueOf(id) : null;

        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(longId).orElseThrow(() -> new BaseException(ResponseCode.BORD_NOT_DETAIL));

        String decryptedUserName = encryptionService.decryptAes(serviceBoard.getUser().getName());
        String thumbnailImgPath = (serviceBoard.getThumbnailImage() != null) ? "/api/images/" + serviceBoard.getThumbnailImage().getStoredFilename() : null;

        return new ServiceBoardResponseDto(
                serviceBoard.getId(),
                serviceBoard.getServiceTitle(),
                serviceBoard.getRecruitCount(),
                serviceBoard.getServiceDate(),
                serviceBoard.getServiceTime(),
                serviceBoard.getDeadline(),
                thumbnailImgPath,
                decryptedUserName,
                serviceBoard.getCategory().getCategoryName(),
                serviceBoard.getServiceContent(),
                serviceBoard.getServiceStat().getStatusName(),
                serviceBoard.getRecruitStat().getStatusName(),
                serviceBoard.getRegDate()
        );
    }
}
