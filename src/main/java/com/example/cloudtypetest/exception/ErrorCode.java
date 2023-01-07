package com.example.cloudtypetest.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 사용자가 존재하지 않습니다.");

    private final String message;
    private final HttpStatus status;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
