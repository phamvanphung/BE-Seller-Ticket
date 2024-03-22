package com.example.ticketsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "vouchers")
@FieldNameConstants
@Accessors(chain=true)
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcType(VarcharJdbcType.class)
    private UUID id ;

    @Column(name = "code")
    private String code;

    @Column(name = "discount")
    private int discount;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Column(name = "used")
    private boolean used;


}
