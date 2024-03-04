package com.example.ticketsystem.dto.user.response;

import com.example.ticketsystem.entity.Role;
import com.example.ticketsystem.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@ToString
public class UserSummaryResponse {
    private String id;
    private String name;
    private String email;
    private LocalDateTime dob;
    private Set<Role> role;

    public UserSummaryResponse(User user) {
         this.id = user.getId().toString();
         this.name = user.getName();
         this.email = user.getEmail();
         this.dob = user.getDob();
         this.role = user.getRoles();
    }

}
