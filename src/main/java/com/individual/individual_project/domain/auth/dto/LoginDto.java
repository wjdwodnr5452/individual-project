package com.individual.individual_project.domain.auth.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;


    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
