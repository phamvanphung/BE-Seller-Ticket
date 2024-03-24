package com.example.ticketsystem.service.impl;

import com.example.ticketsystem.dto.common.response.ApiResponse;
import com.example.ticketsystem.dto.common.response.PageDataResponse;
import com.example.ticketsystem.dto.common.response.StatusResponse;
import com.example.ticketsystem.dto.film.response.FilmResponse;
import com.example.ticketsystem.dto.user.response.UserResponse;
import com.example.ticketsystem.dto.voucher.request.CreateVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.GetAllVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.GiveVoucherRequest;
import com.example.ticketsystem.dto.voucher.request.UpdateVoucherRequest;
import com.example.ticketsystem.dto.voucher.response.VoucherResponse;
import com.example.ticketsystem.entity.Film;
import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.entity.Voucher;
import com.example.ticketsystem.enums.ResponseCode;
import com.example.ticketsystem.enums.Role;
import com.example.ticketsystem.exception.BusinessException;
import com.example.ticketsystem.repository.IUserRepository;
import com.example.ticketsystem.repository.IVoucherRepository;
import com.example.ticketsystem.service.IFilmService;
import com.example.ticketsystem.service.IVoucherService;
import com.example.ticketsystem.utils.CommonUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.runtime.GStringUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoucherService implements IVoucherService {

    private final IVoucherRepository iVoucherRepository;
    private final IUserRepository iUserRepository;
    @Override
    public ResponseEntity<ApiResponse<VoucherResponse>> createVoucher(CreateVoucherRequest request) {
        try {
            String code = CommonUtils.getCodeVoucher();
            Voucher voucher = new Voucher();
            voucher.setCode(code)
                    .setDiscount(request.getDiscount())
                    .setCreateDate(LocalDateTime.now())
                    .setExpiredDate(request.getExpiredDate())
                    .setUsed(false);

            voucher = iVoucherRepository.save(voucher);

            return new ResponseEntity<ApiResponse<VoucherResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new VoucherResponse(voucher)), HttpStatus.OK);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<VoucherResponse>> updateVoucher(UpdateVoucherRequest request) {
        try {
            Voucher voucher = iVoucherRepository.findVoucherByCode(request.getCode()).orElse(null);
            if(Objects.isNull(voucher)){
                throw new BusinessException(ResponseCode.VOUCHER_NOT_FOUND);
            }
            if(Objects.nonNull(request.getDiscount())){
                voucher.setDiscount(request.getDiscount());
            }
            if(Objects.nonNull(request.getExpiredDate())){
                voucher.setExpiredDate(request.getExpiredDate());
            }
            if(StringUtils.isNotBlank(request.getEmail())){
                User user = iUserRepository.findByEmail(request.getEmail()).orElseThrow(
                        () -> new BusinessException(ResponseCode.USER_NOT_FOUND)
                );
                voucher.setUser(user);
            }
            iVoucherRepository.save(voucher);
            return new ResponseEntity<ApiResponse<VoucherResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new VoucherResponse(voucher)), HttpStatus.OK);

        } catch (BusinessException e){
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<VoucherResponse>> getVoucher(String code) {
        try{
            Voucher voucher = iVoucherRepository.findVoucherByCode(code).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.VOUCHER_NOT_FOUND);
                    }
            );
            return new ResponseEntity<ApiResponse<VoucherResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new VoucherResponse(voucher)), HttpStatus.OK);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<StatusResponse>> deleteVoucher(String code, String email) {
        try {
            log.info("email:{}", email);
            User user = iUserRepository.findByEmail(email).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.USER_NOT_FOUND);
                    }
            );
            //log.info("userRole:{}", user.getRoles());
            //log.info("role:{}", Role.ADMIN.name());
            if(!user.getRoles().contains(Role.ADMIN)){
                throw new BusinessException(ResponseCode.USER_DO_NOT_PERMISSION);
            }

            Voucher voucher = iVoucherRepository.findVoucherByCode(code).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.VOUCHER_NOT_FOUND);
                    }
            );
            iVoucherRepository.delete(voucher);
            return new ResponseEntity<ApiResponse<StatusResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new StatusResponse(true)), HttpStatus.OK);

        }catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public PageDataResponse<VoucherResponse> getAllVoucher(GetAllVoucherRequest request) {
        try {
            Page<Voucher> voucherPage = iVoucherRepository.findAll(request, request.getPageable());
            return new PageDataResponse<VoucherResponse>()
                    .setPage(voucherPage.getNumber())
                    .setSize(voucherPage.getSize())
                    .setTotalPage(voucherPage.getTotalPages())
                    .setTotalSize(voucherPage.getSize())
                    .setItems(voucherPage.stream().map(VoucherResponse::new).collect(Collectors.toList()));
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Have error : {}", e.getLocalizedMessage());
            throw new BusinessException(ResponseCode.FAILED);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<VoucherResponse>> giveVoucher(GiveVoucherRequest request, String email) {
        try {
            log.info("email:{}", email);
            User user = iUserRepository.findByEmail(email).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.USER_NOT_FOUND);
                    }
            );
            if(!user.getRoles().contains(Role.ADMIN)){
                throw new BusinessException(ResponseCode.USER_DO_NOT_PERMISSION);
            }
            User user2 = iUserRepository.findByPhone(request.getPhone()).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.USER_NOT_FOUND);
                    }
            );
            Voucher voucher = iVoucherRepository.findVoucherByCode(request.getVoucherCode()).orElseThrow(
                    () -> {
                        throw new BusinessException(ResponseCode.VOUCHER_NOT_FOUND);
                    }
            );
            if(Objects.nonNull(voucher.getUser())){
                throw new BusinessException(ResponseCode.VOUCHER_HAS_GIVEN);
            }
            voucher.setUser(user2);
            iVoucherRepository.save(voucher);

            // bai toan: 1 voucher cho nhieu user

            return new ResponseEntity<ApiResponse<VoucherResponse>>(new ApiResponse<>(ResponseCode.SUCCESS, new VoucherResponse(voucher)), HttpStatus.OK);
        }catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ResponseCode.FAILED);
        }
    }
}
