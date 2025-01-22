package com.individual.individual_project.domain.board;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 작성자 : 정재욱
 * 생성일 : 2025.01.22
 * 엔티티 : categorys
 */

@Data
@Entity
@Table(name = "categorys")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "category_name")
    private String categoryName;


    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
