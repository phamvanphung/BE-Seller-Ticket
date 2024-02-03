package com.example.ticketsystem.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckOtpWhenRegisterRequest {
    private String email;
    @NotBlank
    private String otp;
}
