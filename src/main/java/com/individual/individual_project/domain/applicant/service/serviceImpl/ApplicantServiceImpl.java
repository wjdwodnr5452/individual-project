package com.individual.individual_project.domain.applicant.service.serviceImpl;

import com.individual.individual_project.SessionConst;
import com.individual.individual_project.domain.applicant.Applicant;
import com.individual.individual_project.domain.applicant.repository.ApplicantRepository;
import com.individual.individual_project.domain.applicant.service.ApplicantService;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.repository.ServiceBoardDataJpa;
import com.individual.individual_project.domain.board.repository.StatusRepository;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepository;
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
    public Applicant save(Long id, HttpServletRequest request) {

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
        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(id).orElseThrow(() -> new BaseException(ResponseCode.BORD_NOT_DETAIL));

        applicant.setServiceBoard(serviceBoard);

        Status status = statusRepository.findById(6L).orElseThrow(() -> new BaseException(ResponseCode.STATUS_NOT_FOUND));
        applicant.setApplicantStat(status);

        Applicant save = applicantRepository.save(applicant);

        return save;
    }
}
