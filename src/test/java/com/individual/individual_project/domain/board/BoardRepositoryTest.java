package com.individual.individual_project.domain.board;

import com.individual.individual_project.domain.board.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
@Transactional
public class BoardRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;



    @Test
    void findId() {
        Optional<Category> category = categoryRepository.findById(1L);
        log.info(category.toString());
    }

    @Test
    void getCategorys() {
        List<Category> all = categoryRepository.findAll();
        log.info(all.toString());
    }

}
