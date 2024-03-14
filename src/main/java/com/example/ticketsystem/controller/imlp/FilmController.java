package com.example.ticketsystem.controller.imlp;

import com.example.ticketsystem.controller.IFilmController;
import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.film.request.CreateFilmRequest;
import com.example.ticketsystem.dto.film.request.UpdateFilmRequest;
import com.example.ticketsystem.dto.film.response.FilmResponse;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.service.IFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
public class FilmController implements IFilmController {

    @Autowired
    private IFilmService iFilmService;
    @Override
    public ResponseEntity<ApiResponse<FilmResponse>> createFilm(Principal principal, CreateFilmRequest request) {
        log.info("Has request with data{}", request.toString());
        ResponseEntity<ApiResponse<FilmResponse>> filmResponse = iFilmService.createFilm(request);
        return filmResponse;
    }

    @Override
    public ResponseEntity<ApiResponse<FilmResponse>> getFilm(Principal principal, String name) {
        log.info("Has request with data{}", name);
        ResponseEntity<ApiResponse<FilmResponse>> filmDetail = iFilmService.getFilm(name);
        return filmDetail;
    }

    @Override
    public ResponseEntity<ApiResponse<FilmResponse>> updateFilm(Principal principal, String name, UpdateFilmRequest request) {
        log.info("Update film with data:{}", request.toString());
        ResponseEntity<ApiResponse<FilmResponse>> filmUpdate = iFilmService.updateFilm(name, request);
        return filmUpdate;
    }
}
