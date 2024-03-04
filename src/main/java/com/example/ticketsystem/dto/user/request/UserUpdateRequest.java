package com.example.ticketsystem.dto.user.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserUpdateRequest {

    @Hidden
    @JsonIgnore
    private String id;

    @Hidden
    @JsonIgnore
    private String emailRequest;

    private String phone;
    private String name;
    private String dob;
    private boolean gender;




}
