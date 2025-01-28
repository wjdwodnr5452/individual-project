package com.individual.individual_project.domain.board.service.impl;

import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.repository.ServiceBoardRepository;
import com.individual.individual_project.domain.board.service.ServiceBoardService;
import com.individual.individual_project.domain.response.ResponseCode;
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
import java.util.UUID;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        ServiceBoard serviceBoard = new ServiceBoard(title, Integer.valueOf(recruitCount),
                LocalDateTime.parse(serviceDate, formatter),Integer.valueOf(serviceTime),
                LocalDateTime.parse(deadline, formatter), "",
                userId, Long.valueOf(category), 1L, 3L, content);


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

/*        serviceBoard.setServiceTitle(title);
        serviceBoard.setServiceTime(Integer.valueOf(serviceTime));
        serviceBoard.setRecruitCount(Integer.valueOf(recruitCount));
        serviceBoard.setServiceContent(content);
        serviceBoard.setCategoryId(Long.valueOf(category));
        serviceBoard.setRecruitStatId(1L);
        serviceBoard.setServiceStatId(3L);
        serviceBoard.setServiceDate(LocalDateTime.parse(serviceDate, formatter));
        serviceBoard.setDeadline(LocalDateTime.parse(deadline, formatter));
        serviceBoard.setUserId(userId);*/

        ServiceBoard save = serviceBoardRepository.save(serviceBoard);


        return save;
    }

    @Override
    public void updateServiceBoardStat(LocalDateTime currentTime) {

        serviceBoardRepository.updateServiceStat(currentTime);

    }
}
