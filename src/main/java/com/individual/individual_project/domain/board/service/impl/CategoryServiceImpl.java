package com.individual.individual_project.domain.board.service.impl;

import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.repository.CategoryRepository;
import com.individual.individual_project.domain.board.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {

        List<Category> cateGroyList = categoryRepository.findAll();

        log.info("cateGroyList: {}", cateGroyList);

        return cateGroyList;
    }
}
