package com.example.ticketsystem.repository;

import com.example.ticketsystem.entity.User;
import com.example.ticketsystem.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IVoucherRepository extends JpaRepository<Voucher, UUID>, JpaSpecificationExecutor<Voucher> {
    Optional<Voucher> findVoucherByCode(String code);
}
