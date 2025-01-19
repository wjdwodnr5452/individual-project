package com.individual.individual_project.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



/**
 * 작성자 : 정재욱
 * 생성일 : 2025.01.15
 * 엔티티 : users
 */
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message ="USER_NOT_EMAIL")
    @Email(message ="USER_REGEX_EMAIL")
    private String email;

    @NotNull(message ="USER_NOT_PASSWORD")
    //@Size(min = 8, max = 20, message = "USER_SIZE_PASSWORD")
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).+$",  message = "USER_PATTERN_PASSWORD")
    private String password;

    @NotNull(message = "USER_NOT_NAME")
    private String name;




    @NotNull(message = "USER_NOT_PHONE_NUMBER")
    @Column(name = "phone_number")
    private String phoneNumber;

    public User() {
    }

    public User(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
