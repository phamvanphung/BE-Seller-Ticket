package com.example.ticketsystem.dto.order.request;

import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.enums.TimeChieu;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreateOrderRequest {

    @NotBlank
    private String nameFilm;

    @NotNull
    @Positive
    @Max(10)
    private int quantity;

    @NotNull
    private LocalDateTime expiredDate;

    @NotNull
    private TimeChieu timeChieu;
}
