package com.spring.demo.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed."),
    USERNAME_INVALID(1002, "Username must be at least 6 characters."),
    PASSWORD_INVALID(1002, "Password must be at least 8 characters."),
    PHONE_NUMBER_INVALID(1002, "Phone number must be at least 10 characters."),

    ;


    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
