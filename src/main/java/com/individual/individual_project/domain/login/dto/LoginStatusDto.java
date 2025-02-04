package com.individual.individual_project.domain.login.dto;

import lombok.Data;

@Data
public class LoginStatusDto {

    private Long id;
    private String name;

    public LoginStatusDto() {}

    public LoginStatusDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
