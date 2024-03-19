package com.example.ticketsystem.service;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.film.request.CreateFilmRequest;
import com.example.ticketsystem.dto.film.request.GetAllFilmRequest;
import com.example.ticketsystem.dto.film.request.RatingFilmRequest;
import com.example.ticketsystem.dto.film.request.UpdateFilmRequest;
import com.example.ticketsystem.dto.film.response.FilmResponse;
import com.example.ticketsystem.dto.film.response.FilmSummaryResponse;
import com.example.ticketsystem.entity.Film;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.enums.Role;
import com.example.ticketsystem.exception.BusinessException;
import com.example.ticketsystem.repository.IFilmRepository;
import com.example.ticketsystem.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FilmService implements IFilmService {


    private final IFilmRepository iFilmRepository;
    private final IUserRepository iUserRepository;

    @Override
    public ResponseEntity<ApiResponse<FilmResponse>> createFilm(CreateFilmRequest request) {
        try {
            if (iFilmRepository.existsByName(request.getName())) {
                throw new BusinessException(ResponseCode.FILM_EXISTED);
            } else {
                Film film = new Film();
                film.setName(request.getName());
                film.setTypeFilm(request.getTypeFilm());
                film.setCountry(request.getCountry());
                film.setDescription(request.getDescription());
                film.setImage(request.getImage());
                film.setBeginDate(request.getBeginDate());
                film.setLanguage(request.getLanguage());
                film.setPrice(request.getPrice());
                film.setTime(request.getTime());
                film.setRate(0.0);

                //throw new NullPointerException();
                film = iFilmRepository.save(film);

                return new ResponseEntity<ApiResponse<FilmResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new FilmResponse(film)), HttpStatus.OK);
            }
        } catch (BusinessException e) {
            log.error("this is error{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new BusinessException(ResponseCode.CREATE_FILM_FAILED);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<FilmResponse>> getFilm(String name) {
        try{
            Film film = iFilmRepository.findByName(name).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.FILM_NOT_FOUND);
                    }
            );

            return new ResponseEntity<ApiResponse<FilmResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new FilmResponse(film)), HttpStatus.OK);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<FilmResponse>> updateFilm(String name, UpdateFilmRequest request) {
        try{
            Film film = iFilmRepository.findByName(name).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.FILM_NOT_FOUND);
                    }
            );

            if (StringUtils.isNotBlank(request.getName())) {
                Film filmFindByName = iFilmRepository.findByName(request.getName()).orElse(null);
                if (Objects.nonNull(filmFindByName) && !filmFindByName.getId().equals(film.getId())) {
                    throw new BusinessException(ResponseCode.FILM_EXISTED);
                }
                film.setName(request.getName());
            }

            if (StringUtils.isNotBlank(request.getImage())) {
                film.setImage(request.getImage());
            }
            if (Objects.nonNull(request.getTypeFilm())) {
                film.setTypeFilm(request.getTypeFilm());
            }
            if (request.getTime() != null) {
                film.setTime(request.getTime());
            }

            if (StringUtils.isNotBlank(request.getBeginDate())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dt = LocalDate.parse(request.getBeginDate(), formatter);
                LocalDateTime parsedDate = dt.atStartOfDay();
                film.setBeginDate(parsedDate);
            }
            if (Objects.nonNull((request.getLanguage()))) {
                film.setLanguage(request.getLanguage());
            }
            if (StringUtils.isNotBlank(request.getDescription())) {
                film.setDescription(request.getDescription());
            }
            if (Objects.nonNull(request.getCountry())) {
                film.setCountry(request.getCountry());
            }
            if (request.getRate() != null) {
                film.setRate(request.getRate());
            }
            if (request.getPrice() != null) {
                film.setPrice(request.getPrice());
            }

            iFilmRepository.save(film);
            return new ResponseEntity<ApiResponse<FilmResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new FilmResponse(film)), HttpStatus.OK);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<StatusResponse>> deleteFilm(String name, String email) {
        try {
            User user = iUserRepository.findByEmail(email).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.USER_NOT_FOUND);
                    }
            );
            //log.info("userRole:{}", user.getRoles());
            //log.info("role:{}", Role.ADMIN.name());
            if(!user.getRoles().contains(Role.ADMIN)){
                throw new BusinessException(ResponseCode.USER_DO_NOT_PERMISSION);
            }

            Film film = iFilmRepository.findByName(name).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.FILM_NOT_FOUND);
                    }
            );
            iFilmRepository.delete(film);
            return new ResponseEntity<ApiResponse<StatusResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new StatusResponse(true)), HttpStatus.OK);

        }catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.FAILED);
        }
    }


    @Override
    public PageDataResponse<FilmSummaryResponse> getAllFilm(GetAllFilmRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<StatusResponse>> ratingFilm(RatingFilmRequest request) {
        try {
            Film film = iFilmRepository.findByName(request.getName()).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.FILM_NOT_FOUND);
                    }
            );
            film.setSumRate(film.getSumRate()+ request.getRate());
            film.setCountRate(film.getCountRate()+1);

            Double rate = film.getSumRate()*1.0/ film.getCountRate();
            log.info("rate:{}", rate);
            rate = (double) Math.round(rate * 10 / 10); //lam tron so
            film.setRate(rate);
            iFilmRepository.save(film);

            return new ResponseEntity<ApiResponse<StatusResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new StatusResponse(true)), HttpStatus.OK);

        }catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.FAILED);
        }
    }
}
