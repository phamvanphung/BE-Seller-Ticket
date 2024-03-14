package com.example.ticketsystem.controller;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.user.request.*;
import com.example.ticketsystem.dto.user.response.TokenResponse;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.dto.user.response.UserSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



    @Operation(
        summary = "Verify OTP When Register"
    )
    @PostMapping("/v1/checkOtp/register")
    ResponseEntity<ApiResponse<StatusResponse>> checkOtpWhenRegister(@RequestBody CheckOtpWhenRegisterRequest request);



    @Operation(
            summary = "User update request"
    )
    @PutMapping("/v1/update/{id}")
    ResponseEntity<ApiResponse<UserResponse>> updateUser(Principal principal, @PathVariable(name = "id") String id,@RequestBody UserUpdateRequest request);


    @Operation(
            summary = "Admin list all user"
    )
    @GetMapping("/v1/all")
    ResponseEntity<ApiResponse<PageDataResponse<UserSummaryResponse>>> getPageAll(Principal principal,
                                                     @ParameterObject UserGetPageRequest request);
}
