package com.example.ticketsystem.controller;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.film.request.CreateFilmRequest;
import com.example.ticketsystem.dto.film.request.UpdateFilmRequest;
import com.example.ticketsystem.dto.film.response.FilmResponse;

import com.example.ticketsystem.dto.user.request.UserUpdateRequest;
import com.example.ticketsystem.dto.user.response.UserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "Film controller")
@RequestMapping("/film")
public interface IFilmController {

    @PostMapping("/v1/create")
    ResponseEntity<ApiResponse<FilmResponse>> createFilm(@Valid
                                                         Principal principal,
                                                         @RequestBody CreateFilmRequest request);

    @GetMapping("/v1/get/{name}")
    ResponseEntity<ApiResponse<FilmResponse>> getFilm(@Valid
                                                      Principal principal,
                                                      @PathVariable(name = "name", required = true)
                                                      String name);
    @PutMapping("/v1/update/{name}")
    ResponseEntity<ApiResponse<FilmResponse>> updateFilm(Principal principal,
                                                         @PathVariable(name = "name") String name,
                                                         @RequestBody UpdateFilmRequest request);

}
