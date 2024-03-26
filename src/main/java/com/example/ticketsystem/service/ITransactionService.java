package com.example.ticketsystem.service;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.transaction.request.GetAllTransactionRequest;
import com.example.ticketsystem.dto.transaction.response.TransactionResponse;
import com.example.ticketsystem.dto.voucher.response.VoucherResponse;
import org.springframework.http.ResponseEntity;

public interface ITransactionService {
    ResponseEntity<ApiResponse<TransactionResponse>> getTransaction(String id);

    PageDataResponse<TransactionResponse> getAllTransaction(GetAllTransactionRequest request);
}
