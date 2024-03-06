package com.example.ticketsystem.dto.film.response;

import com.example.ticketsystem.entity.Film;
import com.example.ticketsystem.enums.Country;
import com.example.ticketsystem.enums.Language;
import com.example.ticketsystem.enums.TypeFilm;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class FilmResponse {
    private String id;
    private String image;
    private String name;
    private TypeFilm typeFilm;
    private Integer time;
    private LocalDateTime beginDate;
    private Language language;
    private String description;
    private Country country;
    private Double rate;
    private Boolean deleted;
    private Long price;

    public FilmResponse(Film film){
        this.id = film.getId().toString();
        this.image = film.getImage();
        this.name = film.getName();
        this.typeFilm = film.getTypeFilm();
        this.time = film.getTime();
        this.beginDate = film.getBeginDate();
        this.language = film.getLanguage();
        this.description = film.getDescription();
        this.country = film.getCountry();
        this.rate = film.getRate();
        this.deleted = film.getDeleted();
        this.price = film.getPrice();
    }
}
