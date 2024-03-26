package com.example.ticketsystem.dto.order.response;

import com.example.ticketsystem.entity.Order;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.enums.TimeChieu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class OrderResponse {
    private UUID id ;
    private String code;
    private UUID userId;
    private String filmId;
    private Long prices;
    private int quantity;
    private LocalDateTime expiredDate;
    private TimeChieu timeChieu;

    public OrderResponse(Order order){
        this.id=order.getId();
        this.code=order.getCode();
        this.userId=order.getUser().getId();
        this.filmId=order.getFilmId();
        this.prices=order.getPrices();
        this.quantity=order.getQuantity();
        this.expiredDate=order.getExpiredDate();
        this.timeChieu=order.getTimeChieu();
    }
}
