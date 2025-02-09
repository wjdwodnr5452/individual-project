package com.individual.individual_project.domain.applicant.service.serviceImpl;

import com.individual.individual_project.SessionConst;
import com.individual.individual_project.domain.applicant.Applicant;
import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBordsResponseDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final StatusRepository statusRepository;
    private final ServiceBoardDataJpa serviceBoardDataJpa;

    @Override
    public ApplicantServiceBordsResponseDto save(Long serviceBoardId, HttpServletRequest request) {

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
        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(serviceBoardId).orElseThrow(() -> new BaseException(ResponseCode.BORD_NOT_DETAIL));

        applicant.setServiceBoard(serviceBoard);

        Status status = statusRepository.findById(6L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        applicant.setApplicantStat(status);

        Applicant save = applicantRepository.save(applicant);

        if(save != null){
            ApplicantServiceBordsResponseDto findDto = new ApplicantServiceBordsResponseDto(save.getId(), save.getApplicantStat().getId(), save.getApplicantStat().getStatusName());
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

        Status status = statusRepository.findById(statusId).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));

        applicant.setApplicantStat(status);

        ApplicantServiceBordsResponseDto responseApplicantDto = new ApplicantServiceBordsResponseDto(applicant.getId(), applicant.getApplicantStat().getId(), status.getStatusName());

        return responseApplicantDto;
    }
}
