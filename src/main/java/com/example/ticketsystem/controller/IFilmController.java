package com.example.ticketsystem.controller;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.film.request.CreateFilmRequest;
import com.example.ticketsystem.dto.film.request.GetAllFilmRequest;
import com.example.ticketsystem.dto.film.request.RatingFilmRequest;
import com.example.ticketsystem.dto.film.request.UpdateFilmRequest;
import com.example.ticketsystem.dto.film.response.FilmResponse;

import com.example.ticketsystem.dto.film.response.FilmSummaryResponse;
import com.example.ticketsystem.dto.user.request.UserGetPageRequest;
import com.example.ticketsystem.dto.user.request.UserUpdateRequest;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.dto.user.response.UserSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
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

    @DeleteMapping("/v1/delete/{name}")
    ResponseEntity<ApiResponse<StatusResponse>> deleteFilm(@Valid
                                                      Principal principal,
                                                           @PathVariable(name = "name", required = true)
                                                      String name);

    @Operation(
            summary = "Rating with film name"
    )
    @PostMapping("/v1/rating")
    ResponseEntity<ApiResponse<StatusResponse>> ratingFilm(@Valid
                                                           Principal principal,
                                                           @RequestBody RatingFilmRequest request);

    @Operation(
            summary = "List all film"
    )
    @GetMapping("/v1/all")
    ResponseEntity<ApiResponse<PageDataResponse<FilmSummaryResponse>>> getPageAll(Principal principal,
                                                                                  @ParameterObject GetAllFilmRequest request);
}
