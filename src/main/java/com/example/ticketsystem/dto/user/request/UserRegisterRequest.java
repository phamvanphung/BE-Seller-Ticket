package com.example.ticketsystem.dto.user.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRegisterRequest {
    @Email
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotNull
    private LocalDateTime dob;
    @NotNull
    private boolean gender;

}
