package com.individual.individual_project.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiHeader {
    private int code;
    private String message;
}
