package com.example.ticketsystem.service;


import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.user.request.LoginRequest;
import com.example.ticketsystem.dto.user.request.UserRegisterRequest;
import com.example.ticketsystem.dto.user.response.TokenResponse;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.entity.Role;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.exception.BusinessException;
import com.example.ticketsystem.repository.IUserRepository;
import com.example.ticketsystem.security.bussiness.dto.LoginDto;
import com.example.ticketsystem.security.jwt.JwtUtilities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;


    @Override
    public User saverUser(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public ResponseEntity<ApiResponse<UserResponse>> register(UserRegisterRequest request) {
        try {
            if (iUserRepository.existsByEmailAndDeleted(request.getEmail(), false)) {
                throw new BusinessException(ResponseCode.USER_EMAIL_EXISTED);
            } else {
                User user = new User();
                user.setEmail(request.getEmail());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setPhone(request.getPhone());
                user.setName(request.getName());
                user.setDob(request.getDob());
                user.setGender(request.isGender());
                user.setDeleted(false);
                user.setInactive(false);
                //By Default , he/she is a simple user
                user.setRoles(Set.of(Role.USER));
                user = iUserRepository.save(user);
                return new ResponseEntity<ApiResponse<UserResponse>>(new ApiResponse<>(ResponseCode.SUCCESS,new UserResponse(user)), HttpStatus.OK);
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.USER_REGISTER_FAILED);
        }
    }

    @Override
    public String authenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = iUserRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.name()));
        String token = jwtUtilities.generateToken(user.getUsername(), rolesNames);
        return token;
    }


    @Override
    public ApiResponse<TokenResponse> login(LoginRequest request) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = iUserRepository.findByEmail(authentication.getName()).orElseThrow(() -> new BusinessException(ResponseCode.USER_NOT_FOUND));
            if(user.isDeleted() || user.isInactive()) {
                throw new BusinessException(ResponseCode.USER_DELETED_OR_INACTIVE);
            }
            List<String> rolesNames = new ArrayList<>();
            user.getRoles().forEach(r -> rolesNames.add(r.name()));
            String accessToken = jwtUtilities.generateToken(user.getUsername(), rolesNames);
            return new ApiResponse<>(ResponseCode.SUCCESS,new TokenResponse(user.getEmail(),accessToken));


        } catch (BusinessException e){
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.USER_EMAIL_OR_PASSWORD_INCORRECT);
        }
    }


    @Override
    public ApiResponse<UserResponse> getInfo(String email) {
        try {
            User user = iUserRepository.findByEmail(email).orElseThrow(
                () -> {
                    throw new BusinessException(ResponseCode.USER_NOT_FOUND);
                }
            );
            if(user.isDeleted() || user.isInactive()) {
                throw new BusinessException(ResponseCode.USER_DELETED_OR_INACTIVE);
            }

            return new ApiResponse<>(ResponseCode.SUCCESS,new UserResponse(user));

        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.FAILED);
        }
    }
}

