package com.example.ticketsystem.controller;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.order.request.CreateOrderRequest;
import com.example.ticketsystem.dto.order.request.GetAllOrderRequest;
import com.example.ticketsystem.dto.order.response.OrderResponse;
import com.example.ticketsystem.dto.transaction.request.GetAllTransactionRequest;
import com.example.ticketsystem.dto.transaction.response.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

public interface ITransactionController {
    @Operation(
            summary = "Get infor transaction"
    )
    @PostMapping("/v1/get/{id}")
    ResponseEntity<ApiResponse<TransactionResponse>> getTransaction(@PathVariable(name = "id") String id);

    @Operation(
            summary = "List all transaction"
    )
    @GetMapping("/v1/all")
    ResponseEntity<ApiResponse<PageDataResponse<TransactionResponse>>> getPageAll(@ParameterObject GetAllTransactionRequest request);
}
