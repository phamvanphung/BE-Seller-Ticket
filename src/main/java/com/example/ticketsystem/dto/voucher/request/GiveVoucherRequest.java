package com.example.ticketsystem.dto.voucher.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GiveVoucherRequest {
    @NotBlank
    @Pattern(regexp = "[0-9]+", message = "Phone number must contain only digits")
    private String phone;

    @NotBlank
    private String voucherCode;
}
