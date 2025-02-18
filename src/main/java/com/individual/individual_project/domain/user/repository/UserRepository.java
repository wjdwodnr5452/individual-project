package com.individual.individual_project.domain.user.repository;

import com.individual.individual_project.domain.user.User;
import com.individual.individual_project.domain.user.dto.UserDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT new com.individual.individual_project.domain.user.dto.UserDetailDto( " +
            "u.id, u.email, u.name, u.phoneNumber, coalesce(CAST(sum(at.serviceTime) AS long ),0) ) " +
            "FROM User u " +
            "LEFT JOIN Applicant a ON a.user.id = u.id " +
            "LEFT JOIN ApplicantTime at ON at.applicant.id = a.id " +
            "WHERE u.id = :id " +
            "GROUP BY u.id, u.email, u.name, u.phoneNumber")
    Optional<UserDetailDto> findUserDetail(@Param("id") Long id);

}
