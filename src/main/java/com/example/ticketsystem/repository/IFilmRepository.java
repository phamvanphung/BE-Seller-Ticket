package com.example.ticketsystem.repository;

import com.example.ticketsystem.entity.Film;
import com.example.ticketsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IFilmRepository extends JpaRepository<Film, UUID>, JpaSpecificationExecutor<Film> {

    Boolean existsByNameAndDeleted(String name,boolean deleted );

    Optional<Film> findByName(String name);
}
