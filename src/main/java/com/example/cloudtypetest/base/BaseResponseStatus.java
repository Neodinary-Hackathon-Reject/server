package com.example.cloudtypetest.base;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum   BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),

    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),

    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),

    EMPTY_ACCESS_TOKEN(false,2004, "access token을 입력해주세요."),

    INVALID_ACCESS_TOKEN(false, 2005, "유효하지 않은 ccess Token 입니다."),

    INVALID_USER_JWT(false,403,"권한이 없는 유저의 접근입니다."),

    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 입력해주세요."),
    USERS_EMPTY_USER_PASSWORD(false, 2011, "유저 비밀번호를 입력해주세요."),


    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),

    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),

    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),

    TOO_SHORT_PASSWORD(false, 2018, "비밀번호의 길이를 8자 이상을 설정해주세요."),

    FAILED_TO_SIGN_UP(false, 2019, "회원가입에 실패하였습니다."),

    USERS_EXISTS_ID(false,2020,"중복된 아이디입니다."),

    USERS_EXISTS_NICKNAME(false,2021,"중복된 닉네임입니다."),

    POST_USERS_EMPTY_NICKNAME(false,2022,"닉네임을 입력해주세요."),

    FAILED_TO_LOGIN(false, 2023, "로그인에 실패하였습니다."),

    NOT_EXIST_USER(false, 2024, "존재하지 않는 유저입니다."),

    NOT_EXIST_NICKNAME(false, 2025, "존재하지 않는 닉네임입니다."),

    NOT_EXIST_EMAIL(false, 2026, "존재하지 않는 이메일입니다."),

    NOT_CORRECT_PASSWORD(false, 2027, "비밀번호가 일치하지 않습니다."),

    ALREADY_DELETED_USER(false, 2028, "이미 탈퇴된 유저입니다."),
    NOT_CORRECT_USER(false,2029,"회원 정보에 일치하는 아이디가 없습니다."),



    /**
     * 3000 : Response 오류
     */

    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),

    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),

    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
