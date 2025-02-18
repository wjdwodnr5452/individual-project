package com.individual.individual_project.config;

import com.individual.individual_project.comm.encrypt.EncryptionService;
import com.individual.individual_project.comm.file.FileUploadService;
import com.individual.individual_project.domain.applicant.repository.ApplicantRepository;
import com.individual.individual_project.domain.applicant.repository.ApplicantTimeRepository;
import com.individual.individual_project.domain.board.repository.*;

import com.individual.individual_project.domain.board.service.CategoryService;
import com.individual.individual_project.domain.board.service.ServiceBoardService;
import com.individual.individual_project.domain.board.service.StatusService;
import com.individual.individual_project.domain.board.service.impl.CategoryServiceImpl;
import com.individual.individual_project.domain.board.service.impl.ServiceBoardServiceImpl;
import com.individual.individual_project.domain.board.service.impl.StatusServiceImpl;
import com.individual.individual_project.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BoardBeanConfig {

    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;
    private final ServiceBoardDataJpa serviceBoardDataJpa;
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final FileUploadService fileUploadService;
    private final ThumbnailImageRepository thumbnailImageRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantTimeRepository applicantTimeRepository;


    private final EntityManager em;

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Bean
    public StatusService statusService() {
        return new StatusServiceImpl(statusRepository);
    }

    @Bean
    public ServiceBoardService serviceBoardService() {
        return new ServiceBoardServiceImpl(serviceBoardRepository(), serviceBoardDataJpa,categoryRepository,statusRepository, userRepository, encryptionService, fileUploadService, thumbnailImageRepository, applicantRepository, applicantTimeRepository);
    }

    @Bean
    public ServiceBoardRepository serviceBoardRepository() {
        return new ServiceBoardRepository(em);
    }

}
