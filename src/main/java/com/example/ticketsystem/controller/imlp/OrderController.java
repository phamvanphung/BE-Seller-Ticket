package com.example.ticketsystem.controller.imlp;

import com.example.ticketsystem.controller.IOrderController;
import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.order.request.CreateOrderRequest;
import com.example.ticketsystem.dto.order.request.GetAllOrderRequest;
import com.example.ticketsystem.dto.order.request.UpdateOrderRequest;
import com.example.ticketsystem.dto.order.response.OrderResponse;
import com.example.ticketsystem.dto.voucher.request.CreateVoucherRequest;
import com.example.ticketsystem.dto.voucher.response.VoucherResponse;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.repository.IOrderRepository;
import com.example.ticketsystem.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController implements IOrderController {
    private final IOrderService iOrderService;
    @Override
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(Principal principal, CreateOrderRequest request) {
        return iOrderService.createOrder(request, principal.getName());
    }

    @Override
    public ResponseEntity<ApiResponse<PageDataResponse<OrderResponse>>> getPageAll(GetAllOrderRequest request) {
        PageDataResponse<OrderResponse> orderResponse = iOrderService.getAllOrder(request);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        ResponseCode.SUCCESS,
                        orderResponse),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(UpdateOrderRequest request) {
        return iOrderService.updateOrder(request);
    }
}
