package com.example.ticketsystem.entity;

import com.example.ticketsystem.enums.StatusTransaction;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "transactions")
@FieldNameConstants
@Accessors(chain=true)
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcType(VarcharJdbcType.class)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Order order;

    // 1 voucher for 1 transaction
    @OneToOne
    @JoinColumn(name = "voucherId", referencedColumnName = "id")
    private Voucher voucher;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "netAmount")
    private Long netAmount;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "status")
    private StatusTransaction status;

}
