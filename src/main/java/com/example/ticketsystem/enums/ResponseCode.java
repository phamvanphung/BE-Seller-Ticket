package com.example.ticketsystem.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(0,"Success"),
    FAILED(1,"Failed"),

    USER_NOT_FOUND(10001,"User not found"),
    USER_EMAIL_EXISTED(10002,"User email existed"),
    USER_REGISTER_FAILED(10003,"User register failed"),
    USER_EMAIL_OR_PASSWORD_INCORRECT(10004, "Username or password incorrect"),
    USER_DELETED_OR_INACTIVE(10005,"User deleted or inactive"),

    ;

    private int code;
    private String message;

    ResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }


}
