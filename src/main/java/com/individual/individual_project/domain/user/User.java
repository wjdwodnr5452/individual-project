package com.individual.individual_project.domain.user;

import com.individual.individual_project.domain.validate.SaveCheck;
import com.individual.individual_project.domain.validate.UpdateCheck;
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
    @Size(min = 8, max = 20, message = "USER_SIZE_PASSWORD")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).+$",  message = "비밀번호는 대문자, 소문자, 숫자, 특수문자(!@#$%^&*)를 모두 포함해야 합니다.")
    private String password;

    @NotNull(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotNull(message = "전화번호는 필수 입력 항목입니다.")
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
