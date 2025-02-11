package com.individual.individual_project.domain.board.repository;



import com.individual.individual_project.domain.applicant.QApplicant;
import com.individual.individual_project.domain.board.QCategory;
import com.individual.individual_project.domain.board.QServiceBoard;
import com.individual.individual_project.domain.board.QStatus;
import com.individual.individual_project.domain.board.ServiceBoard;
import com.individual.individual_project.domain.board.dto.ServiceBoardDetailDto;
import com.individual.individual_project.domain.board.service.QThumbnailImge;
import com.individual.individual_project.domain.user.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private static final QApplicant applicant = QApplicant.applicant;
    private static final QThumbnailImge thumbnailImage = QThumbnailImge.thumbnailImge;

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


     public Page<ServiceBoard> findAll(Long serviceStatId, Long recruitStatId, Long categoryId, String serviceBoardSearchName, Pageable pageable) {


         JPQLQuery<ServiceBoard> query = jpaQueryFactory
                 .selectFrom(serviceBoard)
                 .join(serviceBoard.user, user).fetchJoin()
                 .join(serviceBoard.category, category).fetchJoin()
                 .join(serviceBoard.serviceStat, serviceStatus).fetchJoin()
                 .join(serviceBoard.recruitStat, recruitStatus).fetchJoin()
                 .where(
                         buildConditions(serviceStatId,recruitStatId,categoryId,serviceBoardSearchName)
                 );

         // 등록일 내림차순 정렬 추가
         query.orderBy(serviceBoard.regDate.desc());

         // 페이징 처리
         query.offset(pageable.getOffset()) // 페이지의 시작 시점
                 .limit(pageable.getPageSize());

         List<ServiceBoard> result = query.fetch();

         // 총 데이터 개수 구하기
         JPQLQuery<Long> countQuery = jpaQueryFactory
                 .select(serviceBoard.count())
                 .from(serviceBoard)
                 .join(serviceBoard.user, user)
                 .join(serviceBoard.category, category)
                 .join(serviceBoard.serviceStat, serviceStatus)
                 .join(serviceBoard.recruitStat, recruitStatus)
                 .where(buildConditions(serviceStatId, recruitStatId, categoryId, serviceBoardSearchName));

         long totalCount = countQuery.fetchCount();

         return new PageImpl<>(result, pageable, totalCount);
     }


    public ServiceBoardDetailDto findById(Long id) {

        return jpaQueryFactory.select(
                        Projections.constructor(ServiceBoardDetailDto.class,
                                serviceBoard.id,
                                serviceBoard.serviceTitle,
                                serviceBoard.recruitCount,
                                serviceBoard.serviceDate,
                                serviceBoard.serviceTime,
                                serviceBoard.deadline,
                                new CaseBuilder()
                                        .when(thumbnailImage.isNotNull())
                                        .then(thumbnailImage.storedFilename)
                                        .otherwise((String) null),
                                serviceBoard.user.name,
                                category.categoryName,
                                serviceBoard.serviceContent,
                                serviceStatus.statusName,
                                recruitStatus.statusName,
                                serviceBoard.regDate,
                                serviceBoard.serviceStat.id,
                                serviceBoard.recruitStat.id,
                                applicant.count().castToNum(Integer.class).as("userRecruitCount")
                        ))
                .from(serviceBoard)
                .join(serviceBoard.recruitStat, recruitStatus)
                .join(serviceBoard.serviceStat, serviceStatus)
                .leftJoin(applicant).on(applicant.serviceBoard.eq(serviceBoard))
                .leftJoin(thumbnailImage).on(thumbnailImage.id.eq(serviceBoard.thumbnailImage.id))
                .where(serviceBoard.id.eq(id))
                .groupBy(serviceBoard.id)
                .fetchOne(); // 단일 결과 조회
    }


    public Long findBoardWriter(Long id) {

         return  jpaQueryFactory.select(
                 serviceBoard.user.id
         )
                 .from(serviceBoard)
                 .where(serviceBoard.id.eq(id))
                 .fetchOne();
    }

    private BooleanExpression eqUserId(Long userId) {
        if (userId == null) {
           return null;
        }
        return serviceBoard.user.id.eq(userId);
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
