package com.individual.individual_project.domain.board.repository;


import com.individual.individual_project.domain.board.Category;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.dto.ServiceBoardResponseDto;
import com.individual.individual_project.domain.user.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
public class ServiceBoardRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ServiceBoardRepository(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public List<ServiceBoardResponseDto> findAll() {
        return jpaQueryFactory
                .select(Projections.constructor(ServiceBoardResponseDto.class,
                        serviceBoard.id,
                        serviceBoard.serviceTitle,
                        serviceBoard.recruitCount,
                        serviceBoard.serviceDate,
                        serviceBoard.serviceTime,
                        serviceBoard.deadline,
                        serviceBoard.thumbnailImage,
                        user.username,  // user join
                        category.categoryName,  // category join
                        serviceBoard.serviceContent
                ))
                .from(serviceBoard)
                .join(User).on(serviceBoard.userId.eq(user.id))
                .join(Category).on(serviceBoard.categoryId.eq(category.id))
                .fetch();

    }


}
