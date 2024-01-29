package com.example.ticketsystem.controller;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.user.request.LoginRequest;
import com.example.ticketsystem.dto.user.request.UserRegisterRequest;
import com.example.ticketsystem.dto.user.response.TokenResponse;
import com.example.ticketsystem.dto.user.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Tag(name = "User controller")
@RequestMapping("/user")
public interface IUserController {


    @Operation(
        summary = "Đăng ký user"
    )
    @PostMapping("/v1/register")
    ResponseEntity<ApiResponse<UserResponse>> registerUser(@RequestBody UserRegisterRequest request);



    @Operation(
        summary = "Login"
    )
    @PostMapping("/v1/login")
    ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginRequest request);


    @Operation(
        summary = "getInfo"
    )
    @GetMapping("/v1/getInfo")
    ResponseEntity<ApiResponse<UserResponse>> getInfo(Principal principal);


}
