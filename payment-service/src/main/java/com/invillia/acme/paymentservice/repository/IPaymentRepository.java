package com.invillia.acme.paymentservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.paymentservice.model.domain.Payment;

@Repository
public interface IPaymentRepository extends CrudRepository<Payment, Long> {

}
