package com.individual.individual_project.domain.board.service.impl;

import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.repository.CategoryRepository;
import com.individual.individual_project.domain.board.service.CategoryService;
import com.individual.individual_project.domain.response.ResponseCode;
import com.individual.individual_project.web.exception.BaseException;
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

        if(cateGroyList.isEmpty()){
            throw new BaseException(ResponseCode.CATEGORY_NOT_FOUND);
        }


        return cateGroyList;
    }
}
