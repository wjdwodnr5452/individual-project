package com.individual.individual_project.domain.board.repository;



import com.individual.individual_project.domain.board.QCategory;
import com.individual.individual_project.domain.board.QServiceBoard;
import com.individual.individual_project.domain.board.QStatus;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.dto.ServiceBoardResponseDto;
import com.individual.individual_project.domain.user.QUser;
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



     public List<ServiceBoard> findAll() {
/*         QServiceBoard serviceBoard = QServiceBoard.serviceBoard;
         QUser user = QUser.user;
         QCategory category = QCategory.category;
         QStatus status = QStatus.status;

        return jpaQueryFactory
                .select(Projections.fields(ServiceBoardResponseDto.class,
                        serviceBoard.id.as("id"),
                        serviceBoard.serviceTitle.as("serviceTitle"),
                        serviceBoard.recruitCount.as("recruitCount"),
                        serviceBoard.serviceDate.as("serviceDate"),
                        serviceBoard.serviceTime.as("serviceTime"),
                        serviceBoard.deadline.as("deadline"),
                        serviceBoard.thumbnailImage.as("thumbnail"),
                        user.name.as("name"),
                        category.categoryName.as("categoryName"),
                        serviceBoard.serviceContent.as("serviceContent")
                ))
                .from(serviceBoard)
                .join(user).on(serviceBoard.user.id.eq(user.id))
                .join(category).on(serviceBoard.category.id.eq(category.id))
                .fetch();*/

         QServiceBoard serviceBoard = QServiceBoard.serviceBoard;
         QUser user = QUser.user;
         QCategory category = QCategory.category;

         return jpaQueryFactory
                 .selectFrom(serviceBoard)
                 .join(serviceBoard.user, user).fetchJoin()
                 .join(serviceBoard.category, category).fetchJoin()
                 .fetch();
    }



}
