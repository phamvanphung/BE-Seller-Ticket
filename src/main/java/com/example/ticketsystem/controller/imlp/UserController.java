package com.example.ticketsystem.controller.imlp;

import com.example.ticketsystem.controller.IUserController;
import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.user.request.LoginRequest;
import com.example.ticketsystem.dto.user.request.UserRegisterRequest;
import com.example.ticketsystem.dto.user.response.TokenResponse;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController implements IUserController {


    @Autowired
    private IUserService userService;


    @Override
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(UserRegisterRequest request) {
        ResponseEntity<ApiResponse<UserResponse>> userResponseApiResponse = userService.register(request);
        return userResponseApiResponse;
    }


    @Override
    public ResponseEntity<ApiResponse<TokenResponse>> login(LoginRequest request) {
        ApiResponse<TokenResponse> loginResponse = userService.login(request);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> getInfo(Principal principal) {
        ApiResponse<UserResponse> info = userService.getInfo(principal.getName());
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
