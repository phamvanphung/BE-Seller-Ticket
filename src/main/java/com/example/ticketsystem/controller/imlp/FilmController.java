package com.example.ticketsystem.controller.imlp;

import com.example.ticketsystem.controller.IFilmController;
import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.film.request.CreateFilmRequest;
import com.example.ticketsystem.dto.film.request.GetAllFilmRequest;
import com.example.ticketsystem.dto.film.request.RatingFilmRequest;
import com.example.ticketsystem.dto.film.request.UpdateFilmRequest;
import com.example.ticketsystem.dto.film.response.FilmResponse;
import com.example.ticketsystem.dto.film.response.FilmSummaryResponse;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.service.IFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public ResponseEntity<ApiResponse<StatusResponse>> deleteFilm(Principal principal, String name) {
        log.info("Delete film with name:{}", name);
        ResponseEntity<ApiResponse<StatusResponse>> deleteFilm = iFilmService.deleteFilm(name, principal.getName());
        return deleteFilm;
    }

    @Override
    public ResponseEntity<ApiResponse<StatusResponse>> ratingFilm(Principal principal, RatingFilmRequest request) {
        log.info("Rating film with data:{}", request.toString());
        ResponseEntity<ApiResponse<StatusResponse>> ratingFilm = iFilmService.ratingFilm(request);
        return ratingFilm;
    }

    @Override
    public ResponseEntity<ApiResponse<PageDataResponse<FilmSummaryResponse>>> getPageAll(Principal principal, GetAllFilmRequest request) {
        log.info("Has get all film with page:{}", request.toString());
        PageDataResponse<FilmSummaryResponse> filmSummaryResponse = iFilmService.getAllFilm(request);
        return new ResponseEntity<>(
                    new ApiResponse<>(
                        ResponseCode.SUCCESS,
                        filmSummaryResponse),
                    HttpStatus.OK);
    }
}
