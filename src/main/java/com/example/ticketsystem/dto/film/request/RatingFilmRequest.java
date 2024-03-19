package com.example.ticketsystem.dto.film.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RatingFilmRequest {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    @Max(5)
    @Min(1)
    private Long rate;
}
