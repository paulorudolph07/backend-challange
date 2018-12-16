package com.invillia.acme.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.paymentservice.model.domain.Payment;
import com.invillia.acme.paymentservice.service.PaymentService;

@RestController
@RequestMapping(value = "/api/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentRestController {

	@Autowired
	private PaymentService paymentSvc;
	
	@PostMapping
	public ResponseEntity<Payment> pay(@RequestBody Payment payment) {
		return ResponseEntity.ok(paymentSvc.payOrder(payment));
	}
	
}
