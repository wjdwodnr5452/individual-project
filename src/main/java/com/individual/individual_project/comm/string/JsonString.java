package com.individual.individual_project.comm.string;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonString {

    public String toJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String message = objectMapper.writeValueAsString(object);
            return message;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json 직렬화 실패");
        }
    }

}
