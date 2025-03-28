package com.spring.demo.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed.", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(1002, "Username must be at least 6 characters.", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters.", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_INVALID(1004, "Phone number must be at least 10 characters.", HttpStatus.BAD_REQUEST),
    TITLE_INVALID(1005, "Title must be at least 10 characters.", HttpStatus.BAD_REQUEST),
    CONTENT_INVALID(1006, "Content must be at least 10 characters.", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1007, "User not existed.", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1008, "Unauthenticated.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1009, "You do not have permission.", HttpStatus.FORBIDDEN),
    ROLE_EXISTED(1001, "Role existed.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(1010, "Invalid token", HttpStatus.BAD_REQUEST);
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public HttpStatusCode getStatusCode() {return statusCode;}
}
