package com.example.ticketsystem.service;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.film.request.CreateFilmRequest;
import com.example.ticketsystem.dto.film.request.GetAllFilmRequest;
import com.example.ticketsystem.dto.film.request.UpdateFilmRequest;
import com.example.ticketsystem.dto.film.response.FilmResponse;
import com.example.ticketsystem.dto.film.response.FilmSummaryResponse;
import com.example.ticketsystem.dto.user.request.UserRegisterRequest;
import com.example.ticketsystem.dto.user.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface IFilmService {

    ResponseEntity<ApiResponse<FilmResponse>> createFilm(CreateFilmRequest request);

    ResponseEntity<ApiResponse<FilmResponse>> getFilm(String name);

    ResponseEntity<ApiResponse<FilmResponse>> updateFilm(String name, UpdateFilmRequest request);

    PageDataResponse<FilmSummaryResponse> getAllFilm(GetAllFilmRequest request);
}
