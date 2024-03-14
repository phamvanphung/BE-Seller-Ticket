package com.example.ticketsystem.controller.imlp;

import com.example.ticketsystem.controller.IUserController;
import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.user.request.*;
import com.example.ticketsystem.dto.user.response.TokenResponse;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.dto.user.response.UserSummaryResponse;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Objects;

@RestController
@Slf4j
public class UserController implements IUserController {


    @Autowired
    private IUserService userService;

    // TODO: B1. Gui form dank ky.
    @Override
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(UserRegisterRequest request) {
        ResponseEntity<ApiResponse<UserResponse>> userResponseApiResponse = userService.register(request);
        return userResponseApiResponse;
    }

    //TODO: B2. Check OTP
    @Override
    public ResponseEntity<ApiResponse<StatusResponse>> checkOtpWhenRegister(CheckOtpWhenRegisterRequest request) {
        ApiResponse<StatusResponse> checked = userService.checkOtpWhenRegister(request);
        return new ResponseEntity<>(checked, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<TokenResponse>> login(LoginRequest request) {
        log.info("has a request to login:{}", request.toString());
        ApiResponse<TokenResponse> loginResponse = userService.login(request);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> getInfo(Principal principal) {
        ApiResponse<UserResponse> info = userService.getInfo(principal.getName());
        return new ResponseEntity<>(info, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(Principal principal, String id, UserUpdateRequest request) {

        request.setId(id);
        UserResponse userResponse = userService.updateUserInfo(request);
        return new ResponseEntity<>(new ApiResponse<>(ResponseCode.SUCCESS,userResponse),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<PageDataResponse<UserSummaryResponse>>> getPageAll(Principal principal) {
        return null;
    }
}
