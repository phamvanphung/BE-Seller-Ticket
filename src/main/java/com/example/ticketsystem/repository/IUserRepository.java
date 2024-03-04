package com.example.ticketsystem.repository;

import com.example.ticketsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> , JpaSpecificationExecutor<User> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndDeleted(String email, boolean deleted);

    Boolean existsByEmailAndDeleted(String email,boolean deleted );


}


