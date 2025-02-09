package com.individual.individual_project.domain.board.service.impl;

import com.individual.individual_project.SessionConst;
import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.comm.file.FileUploadService;
import com.individual.individual_project.comm.file.UploadFileDto;
import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.dto.ServiceBoardDetailDto;
import com.individual.individual_project.domain.board.dto.ServiceBoardsDto;
import com.individual.individual_project.domain.board.repository.*;
import com.individual.individual_project.domain.board.service.ServiceBoardService;
import com.individual.individual_project.domain.board.service.ThumbnailImge;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepositorySpringData;
import com.individual.individual_project.web.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
    public Page<ServiceBoardsDto> findAll(String serviceStatId, String recruitStatId, String categoryId, String serviceBoardSearchName, Pageable pageable) {

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

            return new ServiceBoardsDto(
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
    public ServiceBoardDetailDto findServiceBoardById(String id, HttpServletRequest request) {

        Long longId = (id != null) ? Long.valueOf(id) : null;

        HttpSession session = request.getSession(false);


        ServiceBoardDetailDto serviceBoardDetailDto = serviceBoardRepository.findById(longId);

        if(serviceBoardDetailDto == null){
            throw new BaseException(ResponseCode.BORD_NOT_DETAIL);
        }

        boolean isWriterCheck = false;

        if (session != null && session.getAttribute(SessionConst.LOGIN_MEMBER) != null){
            User user  = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);

            Long boardWriter = serviceBoardRepository.findBoardWriter(longId);

            boolean equals = boardWriter.equals(user.getId());
            isWriterCheck = equals;
        }

        String thumbnailImgPath = (serviceBoardDetailDto.getThumbnailImage() != null) ? "/api/images/" + serviceBoardDetailDto.getThumbnailImage() : null;

        serviceBoardDetailDto.setWriter(encryptionService.decryptAes(serviceBoardDetailDto.getWriter()));
        serviceBoardDetailDto.setThumbnailImage(thumbnailImgPath);
        serviceBoardDetailDto.setWriterCheck(isWriterCheck);

        return serviceBoardDetailDto;
    }

 /*   @Override
    public ServiceBoardDetailDto findServiceBoardById(String id, HttpServletRequest request) {


        Long longId = (id != null) ? Long.valueOf(id) : null;

        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(longId).orElseThrow(() -> new BaseException(ResponseCode.BORD_NOT_DETAIL));

        HttpSession session = request.getSession(false);

        Boolean isWriterCheck = false;

        if (session != null && session.getAttribute(SessionConst.LOGIN_MEMBER) != null){
            User user = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);

            isWriterCheck = (serviceBoard.getUser().getId().equals(user.getId()) ? true : false);

            log.info("isWriterCheck : {}", isWriterCheck);
        }


        String decryptedUserName = encryptionService.decryptAes(serviceBoard.getUser().getName());
        String thumbnailImgPath = (serviceBoard.getThumbnailImage() != null) ? "/api/images/" + serviceBoard.getThumbnailImage().getStoredFilename() : null;

        return new ServiceBoardDetailDto(
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
                serviceBoard.getRegDate(),
                serviceBoard.getServiceStat().getId(),
                serviceBoard.getRecruitStat().getId(),
                isWriterCheck
        );
    }*/
}
