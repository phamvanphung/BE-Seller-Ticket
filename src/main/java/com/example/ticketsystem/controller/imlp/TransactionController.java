package com.example.ticketsystem.controller.imlp;

import com.example.ticketsystem.controller.ITransactionController;
import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.order.request.GetAllOrderRequest;
import com.example.ticketsystem.dto.order.response.OrderResponse;
import com.example.ticketsystem.dto.transaction.request.GetAllTransactionRequest;
import com.example.ticketsystem.dto.transaction.response.TransactionResponse;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TransactionController implements ITransactionController {

    private final ITransactionService transactionService;
    @Override
    public ResponseEntity<ApiResponse<TransactionResponse>> getTransaction(String id) {
        log.info("has a request with data: {}", id);
        return transactionService.getTransaction(id);
    }

    @Override
    public ResponseEntity<ApiResponse<PageDataResponse<TransactionResponse>>> getPageAll(GetAllTransactionRequest request) {
        log.info("has a request with data: {}", request.toString());
        PageDataResponse<TransactionResponse> transactionResponse = transactionService.getAllTransaction(request);
        return new ResponseEntity<>(
                new ApiResponse<>(ResponseCode.SUCCESS,transactionResponse),
                HttpStatus.OK);
    }
}
