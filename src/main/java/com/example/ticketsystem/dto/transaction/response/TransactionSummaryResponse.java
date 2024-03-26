package com.example.ticketsystem.dto.transaction.response;

import com.example.ticketsystem.entity.*;
import com.example.ticketsystem.enums.Country;
import com.example.ticketsystem.enums.Language;
import com.example.ticketsystem.enums.StatusTransaction;
import com.example.ticketsystem.enums.TypeFilm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class TransactionSummaryResponse {
    private UUID id;
    private User user;
    private Order order;
    private Long netAmount;
    private StatusTransaction status;

    public TransactionSummaryResponse(Transaction transaction){
        this.id=transaction.getId();
        this.user=transaction.getUser();
        this.order=transaction.getOrder();
        this.netAmount=transaction.getNetAmount();
        this.status=transaction.getStatus();
    }

}
