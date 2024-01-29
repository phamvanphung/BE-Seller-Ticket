package com.example.ticketsystem.dto.user.response;


import com.example.ticketsystem.entity.Role;
import com.example.ticketsystem.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String email;
    private String phone;
    private String name;
    private LocalDateTime dob;
    private boolean gender;
    private boolean inactive;
    private boolean deleted;
    private Set<Role> roles;


    public UserResponse(User user) {
        this.id = user.getId().toString();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.dob = user.getDob();
        this.gender = user.isGender();
        this.deleted = user.isDeleted();
        this.inactive = user.isInactive();
        this.roles = user.getRoles();
    }

}
