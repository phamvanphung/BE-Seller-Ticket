package com.example.ticketsystem.entity;

import com.example.ticketsystem.enums.TimeChieu;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "orders")
@FieldNameConstants
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcType(VarcharJdbcType.class)
    private UUID id ;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Column(name = "film_id")
    private String filmId;

    @Column(name = "prices")
    private Long prices;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    @Column(name = "time_chieu")
    private TimeChieu timeChieu;

    @Column(name = "status")
    private Boolean status;

}
