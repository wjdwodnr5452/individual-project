package com.individual.individual_project.config;


import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.domain.applicant.repository.ApplicantRepository;
import com.individual.individual_project.domain.applicant.repository.ApplicantTimeRepository;
import com.individual.individual_project.domain.applicant.service.ApplicantService;
import com.individual.individual_project.domain.applicant.service.serviceImpl.ApplicantServiceImpl;
import com.individual.individual_project.domain.board.repository.ServiceBoardDataJpa;
import com.individual.individual_project.domain.board.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicantBeanConfig {

    private final ApplicantRepository applicantRepository;
    private final StatusRepository statusRepository;
    private final ServiceBoardDataJpa serviceBoardDataJpa;
    private final EncryptionService encryptionService;

    @Bean
    public ApplicantService applicantService() {
        return new ApplicantServiceImpl(applicantRepository,statusRepository,serviceBoardDataJpa, encryptionService);
    }

}
