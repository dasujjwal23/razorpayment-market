package com.dell.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dell.market.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
