package com.individual.individual_project.domain.user.dto;

import lombok.Data;

@Data
public class UserDetailDto {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private Long totalServiceTime;

    public UserDetailDto() {}

    public UserDetailDto(Long id, String email, String name, String phoneNumber, Long totalServiceTime) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.totalServiceTime = totalServiceTime;
    }
}
