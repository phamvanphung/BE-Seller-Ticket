package com.example.ticketsystem.dto.film.response;

import com.example.ticketsystem.entity.Film;
import com.example.ticketsystem.enums.Role;
import com.example.ticketsystem.enums.TypeFilm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
public class FilmSummaryResponse {
    private UUID id;
    private String name;
    private TypeFilm typeFilm;
    private LocalDateTime beginDate;
    private Long price;

    public FilmSummaryResponse(Film film){
        this.id = film.getId();
        this.name = film.getName();
        this.typeFilm = film.getTypeFilm();
        this.beginDate = film.getBeginDate();
        this.price = film.getPrice();
    }
}
