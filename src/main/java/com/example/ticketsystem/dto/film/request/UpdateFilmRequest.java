package com.example.ticketsystem.dto.film.request;

import com.example.ticketsystem.enums.Country;
import com.example.ticketsystem.enums.Language;
import com.example.ticketsystem.enums.TypeFilm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class UpdateFilmRequest {

    @NotNull(message = "Id is mandatory")
    private String name;

    private String image;
    private TypeFilm typeFilm;
    private Integer time;
    private String beginDate;
    private Language language;
    private String description;
    private Country country;

    @PositiveOrZero
    private Double rate;

    private Boolean deleted;

    @Positive
    private Long price;
}
