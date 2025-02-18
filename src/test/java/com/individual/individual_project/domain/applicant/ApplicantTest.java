package com.individual.individual_project.domain.applicant;

import com.individual.individual_project.domain.applicant.dto.ApplicantServiceBoardsDto;
import com.individual.individual_project.domain.applicant.repository.ApplicantRepository;
import com.individual.individual_project.domain.applicant.service.serviceImpl.ApplicantServiceImpl;
import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.Status;
import com.individual.individual_project.domain.board.repository.CategoryRepository;
import com.individual.individual_project.domain.board.repository.ServiceBoardDataJpa;
import com.individual.individual_project.domain.board.repository.StatusRepository;

import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
@Sql(scripts = {"/test_sql/board_sql.sql"})
public class ApplicantTest {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private UserRepository userRepositorySpringData;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ServiceBoardDataJpa serviceBoardDataJpa;

    @Autowired
    ApplicantServiceImpl applicantService;


    @BeforeEach
    void createServiceBoard() {

        User testUser = new User("test@test.com", "123456", "홍길동", "01012341234");
        userRepository.save(testUser);


        User testUser1 = new User("test123@test.com", "123456", "김철수", "01012341234");
        userRepository.save(testUser1);


        User user = userRepositorySpringData.findById(1L).orElseThrow(() -> new RuntimeException("user not found"));
        Category category = categoryRepository.findById(1L).orElseThrow(() -> new RuntimeException("category not found"));
        Status recruitStat = statusRepository.findById(1L).orElseThrow(() -> new RuntimeException("status not found"));
        Status serviceStat = statusRepository.findById(3L).orElseThrow(() -> new RuntimeException("status not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        ServiceBoard serviceBoard = new ServiceBoard("글제목", 3,
                LocalDateTime.parse("2025-01-28T15:30:45.123Z", formatter),Integer.valueOf(3),
                LocalDateTime.parse("2025-01-28T15:30:45.123Z", formatter), null,
                user, category, recruitStat, serviceStat, "글내용");

        serviceBoardDataJpa.save(serviceBoard);
    }

    @Test
    void save() { // 신청자 생성
        Applicant applicant = new Applicant();

        Status status = statusRepository.findById(6L).orElseThrow(() -> new RuntimeException("status not found"));
        User user = userRepositorySpringData.findById(2L).orElseThrow(() -> new RuntimeException("user not found"));
        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(1L).orElseThrow(() -> new RuntimeException("service board not found"));

        applicant.setApplicantDate(LocalDate.now());
        applicant.setUser(user);
        applicant.setServiceBoard(serviceBoard);
        applicant.setApplicantStat(status);

        Applicant save = applicantRepository.save(applicant);

        Assertions.assertThat(save.getServiceBoard().getId()).isEqualTo(serviceBoard.getId());
    }


    @Test
    void findApplicant() {

        Applicant applicant1 = new Applicant();

        Status status = statusRepository.findById(6L).orElseThrow(() -> new RuntimeException("status not found"));
        User user = userRepositorySpringData.findById(1L).orElseThrow(() -> new RuntimeException("user not found"));
        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(1L).orElseThrow(() -> new RuntimeException("service board not found"));

        applicant1.setApplicantDate(LocalDate.now());
        applicant1.setUser(user);
        applicant1.setServiceBoard(serviceBoard);
        applicant1.setApplicantStat(status);

        applicantRepository.save(applicant1);

        Applicant byUserIdAndServiceBoardId = applicantRepository.findByUserIdAndServiceBoardId(user.getId(), serviceBoard.getId()).orElseThrow(() -> new RuntimeException("applicant not found"));

        Assertions.assertThat(byUserIdAndServiceBoardId.getUser().getId()).isEqualTo(user.getId());
    }




    @Test
    void findByApplicantId() { // 봉사 게시판에 신청목록

        Applicant applicant1 = new Applicant();
        Applicant applicant2 = new Applicant();

        Status status = statusRepository.findById(6L).orElseThrow(() -> new RuntimeException("status not found"));
        User user1 = userRepositorySpringData.findById(2L).orElseThrow(() -> new RuntimeException("user not found"));
        User user2 = userRepositorySpringData.findById(2L).orElseThrow(() -> new RuntimeException("user not found"));
        ServiceBoard serviceBoard = serviceBoardDataJpa.findById(1L).orElseThrow(() -> new RuntimeException("service board not found"));

        applicant1.setApplicantDate(LocalDate.now());
        applicant1.setUser(user1);
        applicant1.setServiceBoard(serviceBoard);
        applicant1.setApplicantStat(status);

        applicant2.setApplicantDate(LocalDate.now());
        applicant2.setUser(user2);
        applicant2.setServiceBoard(serviceBoard);
        applicant2.setApplicantStat(status);

        Applicant save = applicantRepository.save(applicant1);
        Applicant save2 = applicantRepository.save(applicant2);

        List<ApplicantServiceBoardsDto> byServiceBoardId = applicantRepository.findByServiceBoardId(1L);

        Assertions.assertThat(byServiceBoardId.size()).isEqualTo(2);

    }


}
