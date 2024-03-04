package com.example.ticketsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "users")
@FieldNameConstants
public class User implements Serializable , UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcType(VarcharJdbcType.class)
    private UUID id ;

    @Column(name = "email")
    private String email;
    @Column(name="password")
    private String password ;


    private String name;

    @Column(name = "dob")
    private LocalDateTime dob;

    @Column
    private String phone;

    private boolean inactive;
    private boolean deleted;
    private boolean gender; // 0: MALE 1 FEMALE
    private String otp;




    //Un utilisateur peut avoir plusieurs roles
    @ElementCollection(targetClass=Role.class) //  specify where JPA can find information about the Enum.
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    @CollectionTable(name="role_user") // create the table that hold relationship from Person to InterestsEnum
    @Column(name="roles") // Column name in person_interest
    private Set<Role> roles ;


    public User (String email , String password , Set<Role> roles) {
      this.email= email ;
      this.password=password ;
      this.roles=roles ;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
