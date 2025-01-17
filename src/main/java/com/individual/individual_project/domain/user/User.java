package com.individual.individual_project.domain.user;

import jakarta.persistence.*;
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
    private String email;
    private String password;
    private String name;
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
