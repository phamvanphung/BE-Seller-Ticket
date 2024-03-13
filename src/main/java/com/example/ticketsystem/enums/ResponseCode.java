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
    USER_CHECK_OTP_FAILED(10006,"OTP incorrect"),
    USER_DO_NOT_PERMISSION(10007,"Do not permission"),


    DATETIME_INVALID(101001,"Date time format invalid"),



    FILM_EXISTED(102001,"Film existed"),
    CREATE_FILM_FAILED(102002, "Create film failed"),
    FILM_NOT_FOUND(102003,"Film not found"),
    FILM_DELETED(102004,"Film deleted"),
    ;

    private int code;
    private String message;

    ResponseCode(int code, String message){
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
