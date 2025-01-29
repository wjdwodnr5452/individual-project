package com.individual.individual_project.config;

import com.individual.individual_project.domain.board.comm.ServiceBoardScheduler;
import com.individual.individual_project.domain.board.repository.CategoryRepository;

import com.individual.individual_project.domain.board.repository.ServiceBoardDataJpa;
import com.individual.individual_project.domain.board.repository.ServiceBoardRepository;
import com.individual.individual_project.domain.board.repository.StatusRepository;
import com.individual.individual_project.domain.board.repository.impl.ServiceBoardRepositoryImpl;
import com.individual.individual_project.domain.board.service.CategoryService;
import com.individual.individual_project.domain.board.service.ServiceBoardService;
import com.individual.individual_project.domain.board.service.StatusService;
import com.individual.individual_project.domain.board.service.impl.CategoryServiceImpl;
import com.individual.individual_project.domain.board.service.impl.ServiceBoardServiceImpl;
import com.individual.individual_project.domain.board.service.impl.StatusServiceImpl;
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
        return new ServiceBoardServiceImpl(serviceBoardRepository());
    }

    @Bean
    public ServiceBoardRepository serviceBoardRepository() {
        return new ServiceBoardRepositoryImpl(em, serviceBoardDataJpa);
    }

}
