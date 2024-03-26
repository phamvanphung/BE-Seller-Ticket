package com.example.ticketsystem.dto.transaction.response;

import com.example.ticketsystem.entity.Order;
import com.example.ticketsystem.entity.Transaction;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.entity.Voucher;
import com.example.ticketsystem.enums.StatusTransaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class TransactionResponse {
    private UUID id;
    private User user;
    private Order order;
    private Voucher voucher;
    private Long amount;
    private Long netAmount;
    private LocalDateTime createDate;
    private StatusTransaction status;

    public TransactionResponse(Transaction transaction){
        this.id=transaction.getId();
        this.user=transaction.getUser();
        this.order=transaction.getOrder();
        this.voucher=transaction.getVoucher();
        this.amount=transaction.getAmount();
        this.netAmount=transaction.getNetAmount();
        this.createDate=transaction.getCreateDate();
        this.status=transaction.getStatus();
    }
}
