package com.example.ticketsystem.dto.common.response;


import com.example.ticketsystem.enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse <T>{

    private int code;
    private String message;
    private T data;


    public ApiResponse(){}

    public ApiResponse(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    public ApiResponse error (String ex){
        this.code = ResponseCode.FAILED.getCode();
        this.message = ex;
        this.data = null;
        return this;
    }


    public ApiResponse<T> ok(T data){
        this.setCode(ResponseCode.SUCCESS.getCode());
        this.setMessage(ResponseCode.SUCCESS.getMessage());
        this.setData(data);
        return this;
    }

}
