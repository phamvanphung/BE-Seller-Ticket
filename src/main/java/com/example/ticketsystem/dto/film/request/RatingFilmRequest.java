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
    @Max(value = 5, message = "Rate must be less than or equal to 5")
    @Min(value = 1, message = "Rate must be greater than or equal to 1")
    private Long rate;
}
