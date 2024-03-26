package com.example.ticketsystem.service;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.order.request.CreateOrderRequest;
import com.example.ticketsystem.dto.order.request.GetAllOrderRequest;
import com.example.ticketsystem.dto.order.request.UpdateOrderRequest;
import com.example.ticketsystem.dto.order.response.OrderResponse;
import com.example.ticketsystem.dto.voucher.request.CreateVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.GetAllVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.UpdateVoucherRequest;
import com.example.ticketsystem.dto.voucher.response.VoucherResponse;
import org.springframework.http.ResponseEntity;

public interface IOrderService {
    ResponseEntity<ApiResponse<OrderResponse>> createOrder(CreateOrderRequest request, String email);
    PageDataResponse<OrderResponse> getAllOrder(GetAllOrderRequest request);

    ResponseEntity<ApiResponse<OrderResponse>> updateOrder(UpdateOrderRequest request);
}
