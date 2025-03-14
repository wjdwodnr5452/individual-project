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


    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, false, "허용되지 않은 메소드입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "서버에 오류가 발생하였습니다."),

    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, false, "권한이 없습니다."),

    /* 유저 */
    // 400 에러
    USER_NOT_EMAIL(HttpStatus.BAD_REQUEST, false, "이메일은 필수 입력 항목입니다."),
    USER_REGEX_EMAIL(HttpStatus.BAD_REQUEST, false, "이메일 형식이 아닙니다."),
    USER_NOT_PASSWORD(HttpStatus.BAD_REQUEST, false, "비밀번호는 필수 입력 항목입니다."),
    USER_SIZE_PASSWORD(HttpStatus.BAD_REQUEST, false, "비밀번호 크기는 8~20자 입니다."),
    USER_PATTERN_PASSWORD(HttpStatus.BAD_REQUEST, false, "비밀번호는 대문자, 소문자, 숫자, 특수문자(!@#$%^&*)를 모두 포함해야 합니다."),
    USER_NOT_NAME(HttpStatus.BAD_REQUEST, false, "이름은 필수 입력 항목입니다."),
    USER_NOT_PHONE_NUMBER(HttpStatus.BAD_REQUEST, false, "전화번호는 필수 입력 항목입니다."),


    // 401 에러
    USER_LOGOUT_STATUS(HttpStatus.UNAUTHORIZED, true, "로그아웃 상태 입니다."),

    // 404 찾을 수 없음
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false, "사용자를 찾을 수 없습니다."),
    USER_NOT_FOUND_EMAIL(HttpStatus.BAD_REQUEST, false, "회원가입 한 이메일이 아닙니다."),
    USER_NOT_FOUND_PASSWORD(HttpStatus.BAD_REQUEST, false, "비밀번호가 틀립니다."),

    // 409 중복
    USER_CONFLICT_EMAIL(HttpStatus.CONFLICT, false, "이미 가입 한 이메일입니다."),

    // 200 OK
    USER_READ_SUCCESS(HttpStatus.OK, true, "사용자 정보 조회 성공"),
    USER_UPDATE_SUCCESS(HttpStatus.OK, true, "사용자 정보 수정 성공"),
    USER_LOGIN_SUCCESS(HttpStatus.OK, true, "사용자 로그인 성공"),
    USER_LOGOUT_SUCCESS(HttpStatus.OK, true, "사용자 로그아웃 성공"),
    USER_LOGIN_STATUS(HttpStatus.OK, true, "로그인 상태 입니다."),


    // 201 Created
    USER_CREATE_SUCCESS(HttpStatus.CREATED, true, "사용자 생성 성공"),
    

    /* 게시판 */

    // 200 OK
    CATEGORY_READ_SUCCESS(HttpStatus.OK, true, "카테고리 리스트 조회 성공"),
    STATUS_READ_SUCCESS(HttpStatus.OK, true, "상태 리스트 조회 성공"),
    BORD_READ_SUCCESS(HttpStatus.OK, true, "게시글 리스트 조회 성공"),
    BORD_STAT_UPDATE_SUCCESS(HttpStatus.OK, true, "게시글 상태 변경 성공"),

    // 201
    BORD_CREATE_SUCCESS(HttpStatus.CREATED, true, "게시판 글 생성 성공"),
    

    // 400 에러
    BORD_UPROAD_FAILD(HttpStatus.BAD_REQUEST, true, "업로드를 실패 했습니다."),
    BORD_NOT_TITLE(HttpStatus.BAD_REQUEST, true, "게시글 제목을 필수 입니다."),
    BORD_NOT_RECRUIT_COUNT(HttpStatus.BAD_REQUEST, true, "모집 인원 작성은 필수 입니다."),
    BORD_NOT_SERVICE_DATE(HttpStatus.BAD_REQUEST, true, "봉사 일정 작성은 필수 입니다."),
    BORD_NOT_SERVICE_TIME(HttpStatus.BAD_REQUEST, true, "봉사 시간 작성은 필수 입니다."),
    BORD_NOT_DEADLINE(HttpStatus.BAD_REQUEST, true, "마감 작성은 필수 입니다."),
    BORD_NOT_USER_ID(HttpStatus.BAD_REQUEST, true, "게시글 작성시 로그인이 필수 입니다."),
    BORD_NOT_CATEGORY_ID(HttpStatus.BAD_REQUEST, true, "게시글 작성시 카테고리는 필수 입니다."),
    BORD_NOT_SERVICE_STAT_ID(HttpStatus.BAD_REQUEST, true, "게시글 작성시 서비스 필수 입니다."),

    // 404 찾을 수 없음
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, false, "카테고리를 찾을 수 없습니다."),
    STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, false, "상태를 찾을 수 없습니다."),
    BORD_NOT_DETAIL(HttpStatus.NOT_FOUND, false, "게시글를 찾을 수 없습니다."),


    /* 신청자 */

    // 201
    APPLICANT_CREATE_SUCCESS(HttpStatus.CREATED, true, "신청 완료"),
    
    // 200
    APPLICANT_READ_SUCCESS(HttpStatus.OK, true, "신청자 조회 성공"),
    APPLICANT_UPDATE_SUCCESS(HttpStatus.OK, true, "신청 수정 성공"),

    // 401 에러
    APPLICANT_COUNT_FULL(HttpStatus.UNAUTHORIZED, true, "모집인원이 꽉찼습니다."),
    APPLICANT_DEADLINE_PASSED(HttpStatus.UNAUTHORIZED, true, "모집일이 종료 되었습니다."),

    // 404 찾을 수 없음
    APPLICANT_NOT_FOUND(HttpStatus.NOT_FOUND, false, "신청자를 찾을 수 없습니다.");



    





    private final HttpStatus httpStatus;
    private final Boolean success;
    private final String message;


    public int getHttpStatusCode() {
        return httpStatus.value();
    }


}
