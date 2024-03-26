package com.example.ticketsystem.service.impl;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.order.request.CreateOrderRequest;
import com.example.ticketsystem.dto.order.request.GetAllOrderRequest;
import com.example.ticketsystem.dto.order.request.UpdateOrderRequest;
import com.example.ticketsystem.dto.order.response.OrderResponse;
import com.example.ticketsystem.dto.voucher.request.UpdateVoucherRequest;
import com.example.ticketsystem.dto.voucher.response.VoucherResponse;
import com.example.ticketsystem.entity.Film;
import com.example.ticketsystem.entity.Order;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.entity.Voucher;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.enums.Role;
import com.example.ticketsystem.exception.BusinessException;
import com.example.ticketsystem.repository.IFilmRepository;
import com.example.ticketsystem.repository.IOrderRepository;
import com.example.ticketsystem.repository.IUserRepository;
import com.example.ticketsystem.repository.IVoucherRepository;
import com.example.ticketsystem.service.IOrderService;
import com.example.ticketsystem.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {

    private final IVoucherRepository iVoucherRepository;
    private final IUserRepository iUserRepository;
    private final IFilmRepository iFilmRepository;
    private final IOrderRepository iOrderRepository;
    @Override
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(CreateOrderRequest request, String email) {
        try {
            //log.info("email:{}", email);
            User user = iUserRepository.findByEmail(email).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.USER_NOT_FOUND);
                    }
            );

            Film film = iFilmRepository.findByName(request.getNameFilm()).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.FILM_NOT_FOUND);
                    }
            );

            Order order = new Order();
            String code = CommonUtils.getCodeOrder();
            order.setCode(code);
            order.setUser(user);
            order.setFilmId(film.getId().toString());
            order.setPrices(film.getPrice()* request.getQuantity());
            order.setQuantity(request.getQuantity());
            order.setExpiredDate(request.getExpiredDate());
            order.setTimeChieu(request.getTimeChieu());
            order.setStatus(false);

            order = iOrderRepository.save(order);
            return new ResponseEntity<ApiResponse<OrderResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new OrderResponse(order)), HttpStatus.OK);

        }catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public PageDataResponse<OrderResponse> getAllOrder(GetAllOrderRequest request) {
        try {
            Page<Order> orderPage = iOrderRepository.findAll(request, request.getPageable());
            return new PageDataResponse<OrderResponse>()
                    .setPage(orderPage.getNumber())
                    .setSize(orderPage.getSize())
                    .setTotalPage(orderPage.getTotalPages())
                    .setTotalSize(orderPage.getSize())
                    .setItems(orderPage.stream().map(OrderResponse::new).collect(Collectors.toList()));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Have error : {}", e.getLocalizedMessage());
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(UpdateOrderRequest request) {
        try {
            Order order = iOrderRepository.findByCode(request.getCode()).orElse(null);
            if(Objects.isNull(order)){
                throw new BusinessException(ResponseCode.ORDER_NOT_FOUND);
            }
            if(Objects.nonNull(request.getQuantity())){
                order.setQuantity(request.getQuantity());
            }
            if(Objects.nonNull(request.getExpiredDate())){
                order.setExpiredDate(request.getExpiredDate());
            }
            if(Objects.nonNull(request.getTimeChieu())){
                order.setTimeChieu(request.getTimeChieu());
            }
            iOrderRepository.save(order);
            return new ResponseEntity<ApiResponse<OrderResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new OrderResponse(order)), HttpStatus.OK);

        } catch (BusinessException e){
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.FAILED);
        }
    }
}
