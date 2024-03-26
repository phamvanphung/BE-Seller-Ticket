package com.example.ticketsystem.service.impl;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.order.response.OrderResponse;
import com.example.ticketsystem.dto.transaction.request.GetAllTransactionRequest;
import com.example.ticketsystem.dto.transaction.response.TransactionResponse;
import com.example.ticketsystem.entity.Film;
import com.example.ticketsystem.entity.Order;
import com.example.ticketsystem.entity.Transaction;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.exception.BusinessException;
import com.example.ticketsystem.repository.ITransactionRepository;
import com.example.ticketsystem.service.ITransactionService;
import com.example.ticketsystem.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    @Override
    public ResponseEntity<ApiResponse<TransactionResponse>> getTransaction(String id) {
        try {
            Transaction transaction = transactionRepository.findById(id).orElseThrow(
                    () -> new BusinessException(ResponseCode.TRANSACTION_NOT_FOUND)
            );

            return new ResponseEntity<ApiResponse<TransactionResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new TransactionResponse(transaction)), HttpStatus.OK);

        }catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public PageDataResponse<TransactionResponse> getAllTransaction(GetAllTransactionRequest request) {
        try {
            Page<Transaction> transactionPage = transactionRepository.findAll(request, request.getPageable());
            return new PageDataResponse<TransactionResponse>()
                    .setPage(transactionPage.getNumber())
                    .setSize(transactionPage.getSize())
                    .setTotalPage(transactionPage.getTotalPages())
                    .setTotalSize(transactionPage.getSize())
                    .setItems(transactionPage.stream().map(TransactionResponse::new).collect(Collectors.toList()));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Have error : {}", e.getLocalizedMessage());
            throw new BusinessException(ResponseCode.FAILED);
        }
    }
}
