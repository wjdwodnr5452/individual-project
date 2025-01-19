package com.individual.individual_project.domain.exception;

import com.individual.individual_project.domain.response.ApiResponse;
import com.individual.individual_project.domain.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // 유효성 검사를 실패한 필드와 메시지를 추출
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        FieldError fieldError = fieldErrors.get(fieldErrors.size()-1);  // 가장 첫 번째 에러 필드

        // DefaultMessage를 기반으로 ResponseCode를 매핑
        ResponseCode responseCode = mapErrorMessageToResponseCode(fieldError.getDefaultMessage());

        log.info("response code: {}", responseCode);
        log.info("response message: {}", responseCode.getMessage());

        // 에러 메시지 반환
        return ApiResponse.fail(responseCode, null);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleBaseException(BaseException ex) {

        ResponseCode responseCode = ex.getResponseCode();

        log.info("message: {}", ex.getMessage());
        log.info("responseCode: {}", responseCode);

        return ApiResponse.fail(responseCode, null);
    }




    // 메시지를 ResponseCode로 매핑하는 메서드
    private ResponseCode mapErrorMessageToResponseCode(String defaultMessage) {
        try {
            // DefaultMessage가 ResponseCode name과 일치한다고 가정
            return ResponseCode.valueOf(defaultMessage);
        } catch (IllegalArgumentException e) {
            // DefaultMessage가 일치하지 않을 경우 기본값 반환
            return ResponseCode.BAD_REQUEST;
        }
    }
}
