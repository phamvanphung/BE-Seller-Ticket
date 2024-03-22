package com.example.ticketsystem.service;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.film.request.CreateFilmRequest;
import com.example.ticketsystem.dto.film.request.GetAllFilmRequest;
import com.example.ticketsystem.dto.film.request.RatingFilmRequest;
import com.example.ticketsystem.dto.film.request.UpdateFilmRequest;
import com.example.ticketsystem.dto.film.response.FilmResponse;
import com.example.ticketsystem.dto.film.response.FilmSummaryResponse;
import com.example.ticketsystem.dto.voucher.request.CreateVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.GetAllVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.UpdateVoucherRequest;
import com.example.ticketsystem.dto.voucher.response.VoucherResponse;
import org.springframework.http.ResponseEntity;

public interface IVoucherService {
    ResponseEntity<ApiResponse<VoucherResponse>> createVoucher(CreateVoucherRequest request);

    ResponseEntity<ApiResponse<VoucherResponse>> updateVoucher(UpdateVoucherRequest request);

    ResponseEntity<ApiResponse<VoucherResponse>> getVoucher(String code);

    PageDataResponse<VoucherResponse> getAllVoucher(GetAllVoucherRequest request);

    ResponseEntity<ApiResponse<StatusResponse>> deleteVoucher(String name, String email);


}
