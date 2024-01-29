package com.example.ticketsystem.security.bussiness.controller;


import com.example.ticketsystem.security.bussiness.dto.LoginDto;
import com.example.ticketsystem.security.bussiness.dto.RegisterDto;
import com.example.ticketsystem.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {


    private final IUserService iUserService;

    //RessourceEndPoint:http://localhost:8087/api/user/register
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
//        return iUserService.register(registerDto);
//    }

    //RessourceEndPoint:http://localhost:8087/api/user/authenticate
//    @PostMapping("/authenticate")
//    public String authenticate(@RequestBody LoginDto loginDto) {
//        return iUserService.authenticate(loginDto);
//    }


}
