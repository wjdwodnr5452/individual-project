package com.individual.individual_project.domain.board;

import com.individual.individual_project.domain.board.repository.CategoryRepository;
import com.individual.individual_project.domain.board.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
@Transactional
public class BoardRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Sql(scripts = {"/test_sql/board_sql.sql"})


    @Test
    void findCategoryId() {
        Optional<Category> category = categoryRepository.findById(1L);

        if(category.isPresent()){
            Assertions.assertThat(category.get().getId()).isEqualTo(1L);
        }else{
            Assertions.assertThat(category).isEmpty();
        }

    }

    @Test
    void findCategorys() {
        List<Category> all = categoryRepository.findAll();

        if(all.size() > 0){
            Assertions.assertThat(categoryRepository.findAll()).hasSize(all.size());
        }
    }

    @Test
    void findStatusId() {

        Optional<Status> status = statusRepository.findById(1L);

        if(status.isPresent()){
            Assertions.assertThat(status.get().getId()).isEqualTo(1L);
        }else{
            Assertions.assertThat(status).isEmpty();
        }

    }

    @Test
    void save() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");


   /*     ServiceBoard serviceBoard = new ServiceBoard("글제목", 3,
                LocalDateTime.parse(serviceDate, formatter),Integer.valueOf(serviceTime),
                LocalDateTime.parse(deadline, formatter), "",
                userId, Long.valueOf(category), 1L, 3L, content);*/


    }




}
