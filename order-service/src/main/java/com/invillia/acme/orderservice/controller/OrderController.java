package com.invillia.acme.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.orderservice.model.domain.Order;
import com.invillia.acme.orderservice.model.domain.OrderItem;
import com.invillia.acme.orderservice.service.OrderService;

@RestController
@RequestMapping(value = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	
	@Autowired
	private OrderService orderSvc;

	@PostMapping
	public ResponseEntity<Order> create(@RequestBody Order order) {
		return ResponseEntity.ok(orderSvc.save(order));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		return ResponseEntity.ok(orderSvc.findById(id));
	}
	
	@GetMapping(value = "/find-by-status/{id}")
	public ResponseEntity<Iterable<Order>> findByOrderStatus(@PathVariable Integer id) {
		return ResponseEntity.ok().body(orderSvc.findAllByStatusId(id));
	}
	
	@PostMapping(value = "/pay-order/{id}")
	public ResponseEntity<Order> payOrder(@PathVariable Long id) {
		return ResponseEntity.ok(orderSvc.payOrder(id));
	}
	
	@PostMapping(value = "/refund/{id}")
	public ResponseEntity<Order> refundOrder(@PathVariable Long id) {
		return ResponseEntity.ok(orderSvc.refundOrder(id));
	}
	
	@PostMapping(value = "/refund-item/{id}")
	public ResponseEntity<OrderItem> refundOrderItem(@PathVariable Long id) {
		return ResponseEntity.ok(orderSvc.refundOrderItem(id));
	}
}
