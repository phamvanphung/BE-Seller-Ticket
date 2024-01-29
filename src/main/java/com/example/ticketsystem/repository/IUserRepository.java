package com.example.ticketsystem.repository;

import com.example.ticketsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    Boolean existsByEmailAndDeleted(String email,boolean deleted );


}


