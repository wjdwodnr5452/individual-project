package com.individual.individual_project.config;

import com.individual.individual_project.domain.board.repository.CategoryRepository;

import com.individual.individual_project.domain.board.repository.StatusRepository;
import com.individual.individual_project.domain.board.service.CategoryService;
import com.individual.individual_project.domain.board.service.StatusService;
import com.individual.individual_project.domain.board.service.impl.CategoryServiceImpl;
import com.individual.individual_project.domain.board.service.impl.StatusServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BoardBeanConfig {

    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Bean
    public StatusService statusService() {
        return new StatusServiceImpl(statusRepository);
    }

}
