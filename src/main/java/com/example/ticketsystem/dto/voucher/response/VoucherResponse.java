package com.example.ticketsystem.dto.voucher.response;

import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.entity.Voucher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class VoucherResponse {

    private String id;
    private String code;
    private int discount;
    private LocalDateTime createDate;
    private LocalDateTime expiredDate;
    private String userId;
    private boolean used;

    public VoucherResponse(Voucher voucher){
        this.id=voucher.getId().toString();
        this.code=voucher.getCode();
        this.discount=voucher.getDiscount();
        this.createDate=voucher.getCreateDate();
        this.expiredDate=voucher.getExpiredDate();
        this.userId= Objects.isNull(voucher.getUser())?null:voucher.getUser().getId().toString();
        this.used=voucher.isUsed();
    }
}
