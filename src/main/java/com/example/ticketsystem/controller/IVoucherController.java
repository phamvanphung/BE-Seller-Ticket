package com.example.ticketsystem.controller;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.film.request.GetAllFilmRequest;
import com.example.ticketsystem.dto.film.response.FilmSummaryResponse;
import com.example.ticketsystem.dto.user.request.UserRegisterRequest;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.dto.voucher.request.CreateVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.GetAllVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.GiveVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.UpdateVoucherRequest;
import com.example.ticketsystem.dto.voucher.response.VoucherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "Voucher controller")
@RequestMapping("/voucher")
public interface IVoucherController {
    @Operation(
            summary = "Create voucher"
    )
    @PostMapping("/v1/create")
    ResponseEntity<ApiResponse<VoucherResponse>> createVoucher(@RequestBody CreateVoucherRequest request);

    @Operation(
            summary = "Update voucher"
    )
    @PutMapping("/v1/update")
    ResponseEntity<ApiResponse<VoucherResponse>> updateVoucher(Principal principal,
                                                               @RequestBody UpdateVoucherRequest request);

    @Operation(
            summary = "Get information of voucher"
    )
    @GetMapping("/v1/get")
    ResponseEntity<ApiResponse<VoucherResponse>> getVoucher(Principal principal, String code);

    @Operation(
            summary = "List all voucher"
    )
    @GetMapping("/v1/all")
    ResponseEntity<ApiResponse<PageDataResponse<VoucherResponse>>> getPageAll(Principal principal,
                                                                                  @ParameterObject GetAllVoucherRequest request);

    @Operation(
            summary = "Delete voucher"
    )
    @DeleteMapping("/v1/delete/{code}")
    ResponseEntity<ApiResponse<StatusResponse>> deleteVoucher(@Valid
                                                           Principal principal,
                                                           @PathVariable(name = "code", required = true)
                                                           String code);

    @PutMapping("/v1/give")
    ResponseEntity<ApiResponse<VoucherResponse>> giveVoucher(Principal principal,
                                                             @RequestBody GiveVoucherRequest request);
}
