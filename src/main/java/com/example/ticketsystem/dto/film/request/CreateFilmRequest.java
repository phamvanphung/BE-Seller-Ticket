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
public class CreateFilmRequest {
    @NotBlank
    private String image;

    @NotBlank
    private String name;

    @NotNull
    private TypeFilm typeFilm;

    @NotNull
    private Integer time;

    @NotNull
    private LocalDateTime beginDate;

    @NotNull
    private Language language;

    private String description;

    @NotNull
    private Country country;

    @NotNull
    @Positive
    private Long price;

}
