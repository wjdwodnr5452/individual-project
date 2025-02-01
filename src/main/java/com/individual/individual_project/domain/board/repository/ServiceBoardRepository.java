package com.individual.individual_project.domain.board.repository;



import com.individual.individual_project.domain.board.QCategory;
import com.individual.individual_project.domain.board.QServiceBoard;
import com.individual.individual_project.domain.board.QStatus;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.user.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;


@Slf4j
@Repository
public class ServiceBoardRepository {

    private final JPAQueryFactory jpaQueryFactory;


    private static final QServiceBoard serviceBoard = QServiceBoard.serviceBoard;
    private static final QUser user = QUser.user;
    private static final QCategory category = QCategory.category;
    private static final QStatus serviceStatus = new QStatus("serviceStatus");
    private static final QStatus recruitStatus = new QStatus("recruitStatus");

    public ServiceBoardRepository(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }


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


     public List<ServiceBoard> findAll(Long serviceStatId, Long recruitStatId, Long categoryId, String serviceBoardSearchName) {


         return jpaQueryFactory
                 .selectFrom(serviceBoard)
                 .join(serviceBoard.user, user).fetchJoin()
                 .join(serviceBoard.category, category).fetchJoin()
                 .join(serviceBoard.serviceStat, serviceStatus).fetchJoin()
                 .join(serviceBoard.recruitStat, recruitStatus).fetchJoin()
                 .where(
                         buildConditions(serviceStatId, recruitStatId, categoryId, serviceBoardSearchName)
                 )
                 .fetch();
    }

    private BooleanBuilder buildConditions(Long serviceStatId, Long recruitStatId, Long categoryId, String serviceBoardSearchName) {
        BooleanBuilder builder = new BooleanBuilder();

        if (categoryId != null) {
            builder.and(category.id.eq(categoryId));
        }
        if (serviceStatId != null) {
            builder.and(serviceStatus.id.eq(serviceStatId));
        }
        if (recruitStatId != null) {
            builder.and(recruitStatus.id.eq(recruitStatId));
        }
        if (StringUtils.hasText(serviceBoardSearchName)) {
            builder.and(serviceBoard.serviceTitle.like("%" + serviceBoardSearchName + "%"));
        }

        return builder;
    }

}
