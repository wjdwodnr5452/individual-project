package com.individual.individual_project.domain.board.service.impl;

import com.individual.individual_project.SessionConst;
import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.comm.file.FileUploadService;
import com.individual.individual_project.comm.file.UploadFileDto;
import com.individual.individual_project.domain.applicant.Applicant;
import com.individual.individual_project.domain.applicant.ApplicantTime;
import com.individual.individual_project.domain.applicant.repository.ApplicantRepository;
import com.individual.individual_project.domain.applicant.repository.ApplicantTimeRepository;
import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.dto.*;
import com.individual.individual_project.domain.board.repository.*;
import com.individual.individual_project.domain.board.service.ServiceBoardService;
import com.individual.individual_project.domain.board.service.ThumbnailImge;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepository;
import com.individual.individual_project.web.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    private final UserRepository userRepository;

    private final EncryptionService encryptionService;
    private final FileUploadService fileUploadService;

    private final ThumbnailImageRepository thumbnailImageRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantTimeRepository applicantTimeRepository;


    @Value("${file.dir}")
    private String fileDir;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    @Override
    public ServiceBoard createServiceBoard(String title, String category, String content, String recruitCount, String serviceTime, String deadline, String serviceDate, MultipartFile thumbnail, Long userId) {


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

        // 모집마감
        Status updateRecruitStat = statusRepository.findById(2L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        serviceBoardDataJpa.updateRecruitStatId(currentTime, updateRecruitStat, 1L);

        // 봉사 진행중
        Status updateServiceStat = statusRepository.findById(4L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        serviceBoardDataJpa.updateServiceStat(currentTime, updateServiceStat, 3L);

        // 봉사 진행중 일 시 신청자 상태 승인으로 변경
        Status updateApplicantStat = statusRepository.findById(7L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        applicantRepository.updateApplicantStat(updateApplicantStat, updateServiceStat, 6L);
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
            String thumbnailImgPath = tumbnailImgUrlPath(serviceBoard.getThumbnailImage());

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

    @Cacheable(cacheNames = "findServiceBoardById", key = "'serviceBoard:'+#id", cacheManager = "cacheManager")
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

    @Override
    public ServiceBoardDetailEditDto findServiceBoardEditById(Long id, HttpServletRequest request) {

        HttpSession session = request.getSession(false);


        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(id).orElseThrow(() -> new BaseException(ResponseCode.BORD_NOT_DETAIL));

        if (session != null && session.getAttribute(SessionConst.LOGIN_MEMBER) != null){
            User user  = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);

            if(!user.getId().equals(serviceBoard.getUser().getId())){
                throw new BaseException(ResponseCode.FORBIDDEN);
            }
        }

        String thumbnailImgPath = (serviceBoard.getThumbnailImage() != null) ? "/api/images/" + serviceBoard.getThumbnailImage().getStoredFilename() : null;

        ServiceBoardDetailEditDto serviceBoardDetailEditDto = new ServiceBoardDetailEditDto(
                serviceBoard.getId(),
                serviceBoard.getServiceTitle(),
                serviceBoard.getCategory().getId(),
                serviceBoard.getRecruitCount(),
                serviceBoard.getServiceTime(),
                serviceBoard.getDeadline(),
                serviceBoard.getServiceDate(),
                serviceBoard.getServiceContent(),
                thumbnailImgPath,
                serviceBoard.getServiceStat().getId(),
                serviceBoard.getRecruitStat().getId()
        );

        return serviceBoardDetailEditDto;
    }

    @Override
    public ServiceBoardDetailEditDto updateServiceBoardEdit(Long id, String title, String category, String content, String recruitCount, String serviceTime, String deadline, String serviceDate, MultipartFile thumbnail, Long userId) {


        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(id).orElseThrow(() -> new BaseException(ResponseCode.BORD_NOT_DETAIL));

        // 변경된 값만 체크하여 업데이트
        if (title != null && !title.equals(serviceBoard.getServiceTitle())) {
            serviceBoard.setServiceTitle(title);
        }

        if (category != null && !category.equals(serviceBoard.getCategory())) {
            Category updateCategory = categoryRepository.findById(Long.valueOf(category)).orElseThrow(() -> new BaseException(ResponseCode.CATEGORY_NOT_FOUND));
            serviceBoard.setCategory(updateCategory);
        }

        if (content != null && !content.equals(serviceBoard.getServiceContent())) {
            serviceBoard.setServiceContent(content);
        }

        if (recruitCount != null && !recruitCount.equals(serviceBoard.getRecruitCount())) {
            serviceBoard.setRecruitCount(Integer.parseInt(recruitCount));
        }

        if (serviceTime != null && !serviceTime.equals(serviceBoard.getServiceTime())) {
            serviceBoard.setServiceTime(Integer.valueOf(serviceTime));
        }

        if (deadline != null && !deadline.equals(serviceBoard.getDeadline().toString())) {
            serviceBoard.setDeadline( LocalDateTime.parse(deadline, formatter));
        }

        if (serviceDate != null && !serviceDate.equals(serviceBoard.getServiceDate().toString())) {
            serviceBoard.setServiceDate(LocalDateTime.parse(serviceDate, formatter));
        }

        if (thumbnail != null && !thumbnail.isEmpty()) {
            UploadFileDto uploadFileDto = fileUploadService.storeFile(thumbnail);

            ThumbnailImge thumbnailImge = new ThumbnailImge();
            thumbnailImge.setStoredFilename(uploadFileDto.getStoreFileName());
            thumbnailImge.setOriginalFilename(uploadFileDto.getUploadFileName());

            ThumbnailImge thumbnailImgeSave = thumbnailImageRepository.save(thumbnailImge);

            serviceBoard.setThumbnailImage(thumbnailImgeSave);
        }

        ServiceBoard saveServiceBoard = serviceBoardDataJpa.save(serviceBoard);
        String tumbnailImgUrl= tumbnailImgUrlPath(saveServiceBoard.getThumbnailImage());


        ServiceBoardDetailEditDto serviceBoardDetailEditDto = new ServiceBoardDetailEditDto(
                saveServiceBoard.getId(),
                saveServiceBoard.getServiceTitle(),
                saveServiceBoard.getCategory().getId(),
                saveServiceBoard.getRecruitCount(),
                saveServiceBoard.getServiceTime(),
                saveServiceBoard.getDeadline(),
                saveServiceBoard.getServiceDate(),
                saveServiceBoard.getServiceContent(),
                tumbnailImgUrl,
                saveServiceBoard.getServiceStat().getId(),
                serviceBoard.getRecruitStat().getId()
        );


        return serviceBoardDetailEditDto;
    }

    @Override
    public Status saveServiceTimeAndComplete(Long id, List<SaveApplicantServiceTimeDto> saveApplicantServiceTimeDto) {

        List<ApplicantTime> applicantTimes = saveApplicantServiceTimeDto.stream()
                .map(dto -> new ApplicantTime(findApplicantByApplicantId(dto.getApplicantId()), dto.getServiceTime()))
                .collect(Collectors.toList());

        applicantTimeRepository.saveAll(applicantTimes);

        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(id).orElseThrow(() -> new BaseException(ResponseCode.BORD_NOT_DETAIL));

        Status status = statusRepository.findById(5L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        serviceBoard.setServiceStat(status);

        return status;
    }

    @Override
    public List<UserWriteServiceBoardDto> findServiceBoardByUserId(Long userId, HttpServletRequest request) {


        List<UserWriteServiceBoardDto> allByUserId = serviceBoardRepository.findAllByUserId(userId);

        log.info("allByUserId : {}", allByUserId);
        return allByUserId;
    }


    private String tumbnailImgUrlPath(ThumbnailImge tumbnailImg) {

        String thumbnailImgPath = (tumbnailImg != null) ? "/api/images/" + tumbnailImg.getStoredFilename() : null;

        return thumbnailImgPath;
    }

    private Applicant findApplicantByApplicantId(Long applicantId) {
        Applicant findApplicant = applicantRepository.findById(applicantId).orElseThrow(()-> new BaseException(ResponseCode.APPLICANT_NOT_FOUND));
        return findApplicant;
    }

}