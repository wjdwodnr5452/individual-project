package com.individual.individual_project.domain.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ResponseCode {

    /* 공통 */
    // 400 에러
    BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 요청입니다."),


    /* 유저 */
    // 400 에러
    USER_NOT_EMAIL(HttpStatus.BAD_REQUEST, false, "이메일은 필수 입력 항목입니다."),
    USER_REGEX_EMAIL(HttpStatus.BAD_REQUEST, false, "이메일 형식이 아닙니다."),

    
    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, false, "권한이 없습니다."),

    // 404 Not Found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false, "사용자를 찾을 수 없습니다."),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, false, "허용되지 않은 메소드입니다."),

    // 409 Conflict
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, false, "이미 가입한 사용자입니다."),
    USER_NAME_ALREADY_EXIST(HttpStatus.CONFLICT, false, "이미 존재하는 닉네임입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "서버에 오류가 발생하였습니다."),


    // 200 OK
    USER_READ_SUCCESS(HttpStatus.OK, true, "사용자 정보 조회 성공"),
    USER_UPDATE_SUCCESS(HttpStatus.OK, true, "사용자 정보 수정 성공"),
    USER_LOGIN_SUCCESS(HttpStatus.OK, true, "사용자 로그인 성공"),
    // 201 Created
    USER_CREATE_SUCCESS(HttpStatus.CREATED, true, "사용자 생성 성공");

    private final HttpStatus httpStatus;
    private final Boolean success;
    private final String message;


    public int getHttpStatusCode() {
        return httpStatus.value();
    }


}
