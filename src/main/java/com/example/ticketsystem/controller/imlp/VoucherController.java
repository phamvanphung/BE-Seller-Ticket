package com.example.ticketsystem.controller.imlp;

import com.example.ticketsystem.controller.IVoucherController;
import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.film.response.FilmSummaryResponse;
import com.example.ticketsystem.dto.voucher.request.CreateVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.GetAllVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.UpdateVoucherRequest;
import com.example.ticketsystem.dto.voucher.response.VoucherResponse;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.service.impl.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class VoucherController implements IVoucherController {

    private final VoucherService voucherService;
    @Override
    public ResponseEntity<ApiResponse<VoucherResponse>> createVoucher(CreateVoucherRequest request) {
        return voucherService.createVoucher(request);
    }

    @Override
    public ResponseEntity<ApiResponse<VoucherResponse>> updateVoucher(Principal principal,
                                                                      UpdateVoucherRequest request) {
        return voucherService.updateVoucher(request);
    }

    @Override
    public ResponseEntity<ApiResponse<VoucherResponse>> getVoucher(Principal principal, String code) {
        return voucherService.getVoucher(code);
    }

    @Override
    public ResponseEntity<ApiResponse<PageDataResponse<VoucherResponse>>> getPageAll(Principal principal, GetAllVoucherRequest request) {
        PageDataResponse<VoucherResponse> voucherResponse = voucherService.getAllVoucher(request);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        ResponseCode.SUCCESS,
                        voucherResponse),
                        HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<StatusResponse>> deleteVoucher(Principal principal, String code) {
        return voucherService.deleteVoucher(code, principal.getName());
    }

}
