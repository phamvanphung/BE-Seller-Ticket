package com.example.ticketsystem.repository;

import com.example.ticketsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> , JpaSpecificationExecutor<User> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Optional<User> findByEmailAndDeleted(String email, boolean deleted);

    Boolean existsByEmailAndDeleted(String email,boolean deleted );


}


