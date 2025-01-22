package com.individual.individual_project.config;

import com.individual.individual_project.domain.board.repository.CategoryRepository;

import com.individual.individual_project.domain.board.service.CategoryService;
import com.individual.individual_project.domain.board.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BoardBeanConfig {

    private final CategoryRepository categoryRepository;

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryRepository);
    }

}
