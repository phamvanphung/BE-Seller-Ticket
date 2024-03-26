package com.example.ticketsystem.controller;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "Order controller")
@RequestMapping("/order")
public interface IOrderController {
    @Operation(
            summary = "Create order"
    )
    @PostMapping("/v1/create")
    ResponseEntity<ApiResponse<OrderResponse>> createOrder(Principal principal,
                                                           @RequestBody CreateOrderRequest request);

    @Operation(
            summary = "List all order"
    )
    @GetMapping("/v1/all")
    ResponseEntity<ApiResponse<PageDataResponse<OrderResponse>>> getPageAll(@ParameterObject GetAllOrderRequest request);

    @Operation(
            summary = "Update order"
    )
    @PutMapping("/v1/update")
    ResponseEntity<ApiResponse<OrderResponse>> updateOrder(@RequestBody UpdateOrderRequest request);
}
