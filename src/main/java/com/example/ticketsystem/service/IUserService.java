package com.example.ticketsystem.service;



import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.user.request.CheckOtpWhenRegisterRequest;
import com.example.ticketsystem.dto.user.request.LoginRequest;
import com.example.ticketsystem.dto.user.request.UserRegisterRequest;
import com.example.ticketsystem.dto.user.response.TokenResponse;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.entity.Role;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.security.bussiness.dto.LoginDto;
import com.example.ticketsystem.security.bussiness.dto.RegisterDto;
import org.springframework.http.ResponseEntity;


public interface IUserService {


    String authenticate(LoginDto loginDto);


    ResponseEntity<ApiResponse<UserResponse>> register(UserRegisterRequest request);

    ApiResponse<TokenResponse> login(LoginRequest request);

    ApiResponse<UserResponse> getInfo(String email);

    User saverUser(User user);

    ApiResponse<StatusResponse> checkOtpWhenRegister(CheckOtpWhenRegisterRequest request);
}
