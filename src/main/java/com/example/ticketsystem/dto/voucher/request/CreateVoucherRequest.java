package com.example.ticketsystem.dto.voucher.request;

import com.example.ticketsystem.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CreateVoucherRequest {

    @NotNull
    @Positive
    private int discount;

    @NotNull
    private LocalDateTime expiredDate;

//    @NotBlank
//    private String email;

}
