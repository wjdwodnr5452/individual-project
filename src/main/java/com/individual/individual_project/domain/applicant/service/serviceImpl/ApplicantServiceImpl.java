package com.individual.individual_project.domain.applicant.service.serviceImpl;

import com.individual.individual_project.SessionConst;
import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.comm.string.JsonString;
import com.individual.individual_project.domain.applicant.Applicant;
import com.individual.individual_project.domain.applicant.dto.ApplicantKafkaMessage;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBoardsDto;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBordsResponseDto;
import com.individual.individual_project.domain.applicant.dto.ApplicantUserDto;
import com.individual.individual_project.domain.applicant.repository.ApplicantRepository;
import com.individual.individual_project.domain.applicant.service.ApplicantService;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.repository.ServiceBoardDataJpa;
import com.individual.individual_project.domain.board.repository.StatusRepository;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.web.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final StatusRepository statusRepository;
    private final ServiceBoardDataJpa serviceBoardDataJpa;
    private final EncryptionService encryptionService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonString jsonString;

    @Value("${file.dir}")
    private String fileDir;



    @Override
    public ApplicantServiceBordsResponseDto save(Long serviceBoardId, HttpServletRequest request) {

        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(serviceBoardId).orElseThrow(() -> new BaseException(ResponseCode.BORD_NOT_DETAIL));


        boolean serviceBoardApplicantChk = countByServiceBoardId(serviceBoardId, serviceBoard.getRecruitCount()); // 모집인원이 다 꽉찼는지 확인

        if (serviceBoard.getRecruitStat().getId() == 2){
            throw new BaseException(ResponseCode.APPLICANT_DEADLINE_PASSED);
        }

        if(!serviceBoardApplicantChk) {
            throw new BaseException(ResponseCode.APPLICANT_COUNT_FULL); // 모집인원 꽉참
        }

        HttpSession session = request.getSession(false);

        LocalDate localDate = LocalDate.now();

        Applicant applicant = new Applicant();
        applicant.setApplicantDate(localDate);

        if (session != null && session.getAttribute(SessionConst.LOGIN_MEMBER) != null){
            User user = (User)  session.getAttribute(SessionConst.LOGIN_MEMBER);
            applicant.setUser(user);
        }else{
            throw new BaseException(ResponseCode.USER_NOT_FOUND);
        }
        
        applicant.setServiceBoard(serviceBoard);

        Status status = statusRepository.findById(6L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        applicant.setApplicantStat(status);

        Applicant save = applicantRepository.save(applicant);

        if(save != null){
            ApplicantServiceBordsResponseDto findDto = new ApplicantServiceBordsResponseDto(save.getId(), save.getApplicantStat().getId(), save.getApplicantStat().getStatusName());

            ApplicantKafkaMessage applicantKafkaMessage = new ApplicantKafkaMessage(
                    save.getServiceBoard().getId(),
                    save.getUser().getId(),
                    save.getApplicantStat().getId()
            );


            this.kafkaTemplate.send("applicant.send", jsonString.toJsonString(applicantKafkaMessage));

            return findDto;
        }else{
            throw new BaseException(ResponseCode.BAD_REQUEST);
        }

    }


    @Override
    public ApplicantServiceBordsResponseDto findApplicant(Long userId, Long serviceBoardId) {

        Optional<Applicant> findApplicant = applicantRepository.findByUserIdAndServiceBoardId(userId, serviceBoardId);

        if(findApplicant.isPresent()){
            Applicant applicant = findApplicant.get();

            ApplicantServiceBordsResponseDto serviceBordsFindDto = new ApplicantServiceBordsResponseDto(applicant.getId(), applicant.getApplicantStat().getId(), applicant.getApplicantStat().getStatusName());

            return serviceBordsFindDto;
        }else{
            return null;
        }
    }

    @Override
    public ApplicantServiceBordsResponseDto updateApplicantStat(Long id, Long statusId) {


        Applicant applicant = applicantRepository.findById(id).orElseThrow(() -> new BaseException(ResponseCode.APPLICANT_NOT_FOUND));

        if(statusId == 6) {

            boolean serviceBoardApplicantChk = countByServiceBoardId(applicant.getServiceBoard().getId(), applicant.getServiceBoard().getRecruitCount());

            if (applicant.getServiceBoard().getRecruitStat().getId() == 2){
                throw new BaseException(ResponseCode.APPLICANT_DEADLINE_PASSED);
            }

            if(!serviceBoardApplicantChk) {
                throw new BaseException(ResponseCode.APPLICANT_COUNT_FULL); // 모집인원 꽉참
            }

        }

        Status status = statusRepository.findById(statusId).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));

        applicant.setApplicantStat(status);

        ApplicantServiceBordsResponseDto responseApplicantDto = new ApplicantServiceBordsResponseDto(applicant.getId(), applicant.getApplicantStat().getId(), status.getStatusName());

        ApplicantKafkaMessage applicantKafkaMessage = new ApplicantKafkaMessage(
                applicant.getServiceBoard().getId(),
                applicant.getUser().getId(),
                applicant.getApplicantStat().getId()
        );


        this.kafkaTemplate.send("applicant.send", jsonString.toJsonString(applicantKafkaMessage));


        return responseApplicantDto;
    }

    @Override
    public List<ApplicantServiceBoardsDto> findByServiceBoardId(Long serviceBoardId) {

        List<ApplicantServiceBoardsDto> byServiceBoardId = applicantRepository.findByServiceBoardId(serviceBoardId);

        if (byServiceBoardId.size() > 0) {
            for (int i =0; i<byServiceBoardId.size(); i++) {
                byServiceBoardId.get(i).setUserName(encryptionService.decryptAes(byServiceBoardId.get(i).getUserName()));
                byServiceBoardId.get(i).setPhoneNumber(encryptionService.decryptAes(byServiceBoardId.get(i).getPhoneNumber()));
            }
        }

        return byServiceBoardId;
    }

    @Override
    public List<ApplicantUserDto> findByUsers(Long id, HttpServletRequest request) {

        List<ApplicantUserDto> applicantUser = applicantRepository.findByUserId(id);

        log.info("applicantUser = {}" , applicantUser);

        return applicantUser;
    }


    private boolean countByServiceBoardId(Long serviceBoardId, Integer recuritCount) {

        Long count = applicantRepository.countByServiceBoardIdAndApplicantStatId(serviceBoardId, 6L);

        if(count < recuritCount){
            return true;
        }else{
            return false;
        }

    }

    private Applicant findApplicantByApplicantId(Long applicantId) {
       Applicant findApplicant = applicantRepository.findById(applicantId).orElseThrow(()-> new BaseException(ResponseCode.APPLICANT_NOT_FOUND));
       return findApplicant;
    }

}
