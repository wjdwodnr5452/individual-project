package com.individual.individual_project.domain.board.repository;

import com.individual.individual_project.domain.board.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {

}
