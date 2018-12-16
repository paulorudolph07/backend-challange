package com.invillia.acme.paymentservice.service;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.invillia.acme.paymentservice.exception.OrderNotFoundException;
import com.invillia.acme.paymentservice.model.domain.Payment;
import com.invillia.acme.paymentservice.repository.IPaymentRepository;
import com.invillia.acme.paymentservice.repository.IPaymentStatusRepository;
import com.invillia.acme.paymentservice.restclient.OrderRestClient;

@Service
@Transactional
@Validated
public class PaymentService {
	
	@Autowired
	private IPaymentRepository paymentRepo;
	
	@Autowired
	private IPaymentStatusRepository paymentStatusRepo;
	
	@Autowired
	private OrderRestClient orderRestClient;
	
	public Payment payOrder(@Valid Payment payment) {
		try {
			orderRestClient.payOrder(payment.getOrderId());
			return save(payment);
		} catch (EntityNotFoundException e) {
			throw new OrderNotFoundException(e.getMessage());
		}
	}
	
	public Payment save(@Valid Payment payment) {
		payment.setPaymentDate(LocalDate.now());
		// Authorized
		payment.setStatus(paymentStatusRepo.findById(2).get());
		return paymentRepo.save(payment);
	}
}
