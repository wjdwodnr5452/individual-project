package com.individual.individual_project.domain.board.service.impl;

import com.individual.individual_project.comm.EncryptionService;
import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.dto.ServiceBoardResponseDto;
import com.individual.individual_project.domain.board.repository.CategoryRepository;
import com.individual.individual_project.domain.board.repository.ServiceBoardDataJpa;
import com.individual.individual_project.domain.board.repository.ServiceBoardRepository;
import com.individual.individual_project.domain.board.repository.StatusRepository;
import com.individual.individual_project.domain.board.service.ServiceBoardService;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepositorySpringData;
import com.individual.individual_project.web.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private final UserRepositorySpringData userRepository;
    private final EncryptionService encryptionService;

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public ServiceBoard createServiceBoard(String title, String category, String content, String recruitCount, String serviceTime, String deadline, String serviceDate, MultipartFile thumbnail, Long userId) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        Category categoryEntity = categoryRepository.findById(Long.valueOf(category)).orElseThrow(() -> new BaseException(ResponseCode.CATEGORY_NOT_FOUND));
        Status recruitStatEntity = statusRepository.findById(1L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        Status serviceStatEntity = statusRepository.findById(3L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(ResponseCode.USER_NOT_FOUND));

        ServiceBoard serviceBoard = new ServiceBoard(title, Integer.valueOf(recruitCount),
                LocalDateTime.parse(serviceDate, formatter),Integer.valueOf(serviceTime),
                LocalDateTime.parse(deadline, formatter), "",
                user, categoryEntity, recruitStatEntity, serviceStatEntity, content);


        if(thumbnail != null && !thumbnail.isEmpty()){
            String thumbnailPath = fileDir  + thumbnail.getOriginalFilename();
            serviceBoard.setThumbnailImage(thumbnailPath);
            try {
                thumbnail.transferTo(new File(thumbnailPath));
            } catch (IOException e) {
                log.info("업로드에 실패 했습니다.");
                throw new BaseException(ResponseCode.BORD_UPROAD_FAILD);
            }
        }

        ServiceBoard save = serviceBoardDataJpa.save(serviceBoard);

        return save;
    }

    @Override
    public void updateServiceBoardStat(LocalDateTime currentTime) {

        Status updateServiceStat = statusRepository.findById(4L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        serviceBoardDataJpa.updateServiceStat(currentTime, updateServiceStat, 3L);

        Status updateRecruitStat = statusRepository.findById(4L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        serviceBoardDataJpa.updateRecruitStatId(currentTime, updateRecruitStat, 1L);
    }

    @Override
    public List<ServiceBoardResponseDto> findAll(String serviceStatId, String recruitStatId, String categoryId, String serviceBoardSearchName) {
        List<ServiceBoard> serviceBoards = serviceBoardRepository.findAll();

        // DTO 변환 및 복호화 처리
        return serviceBoards.stream()
                .map(serviceBoard -> {
                    String decryptedUserName = encryptionService.decryptAes(serviceBoard.getUser().getName());
                    return new ServiceBoardResponseDto(
                            serviceBoard.getId(),
                            serviceBoard.getServiceTitle(),
                            serviceBoard.getRecruitCount(),
                            serviceBoard.getServiceDate(),
                            serviceBoard.getServiceTime(),
                            serviceBoard.getDeadline(),
                            serviceBoard.getThumbnailImage(),
                            decryptedUserName,
                            serviceBoard.getCategory().getCategoryName(),
                            serviceBoard.getServiceContent()
                    );
                })
                .collect(Collectors.toList());
    }
}
