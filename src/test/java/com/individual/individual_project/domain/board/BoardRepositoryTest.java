package com.individual.individual_project.domain.board;

import com.individual.individual_project.domain.board.comm.ServiceBoardScheduler;
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
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Import(ServiceBoardScheduler.class)
@SpringBootTest
@Transactional
public class BoardRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ServiceBoardDataJpa serviceBoardDataJpa;

    @Autowired
    private UserRepository userRepository;



    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("test@test.com", "123456", "홍길동", "01012341234");
        userRepository.saveUser(testUser);
    }

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

        ServiceBoard serviceBoard = new ServiceBoard("글제목", 3,
                LocalDateTime.parse("2025-01-28T15:30:45.123Z", formatter),Integer.valueOf(3),
                LocalDateTime.parse("2025-01-28T15:30:45.123Z", formatter), "",
                1L, Long.valueOf(1), 1L, 3L, "글내용");

        ServiceBoard saveBoard = serviceBoardDataJpa.save(serviceBoard);

        Assertions.assertThat(saveBoard.getId()).isEqualTo(1L);
    }




}
