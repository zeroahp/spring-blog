package com.spring.demo.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed."),
    USERNAME_INVALID(1002, "Username must be at least 6 characters."),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters."),
    PHONE_NUMBER_INVALID(1004, "Phone number must be at least 10 characters."),
    TITLE_INVALID(1005, "Title must be at least 10 characters."),
    CONTENT_INVALID(1006, "Content must be at least 10 characters."),
    USER_NOT_EXISTED(1007, "User not existed."),
    UNAUTHENTICATED(1008, "Unauthenticated."),

    ;
    private int code;
    private String message;


    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
