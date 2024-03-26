package com.example.ticketsystem.dto.order.request;

import com.example.ticketsystem.enums.TimeChieu;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UpdateOrderRequest {
    @NotBlank
    private String code;

    @Positive
    @Max(10)
    private int quantity;

    private LocalDateTime expiredDate;

    private TimeChieu timeChieu;
}
