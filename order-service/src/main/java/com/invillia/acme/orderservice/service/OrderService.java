package com.invillia.acme.orderservice.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.invillia.acme.orderservice.exception.RefundException;
import com.invillia.acme.orderservice.model.domain.Order;
import com.invillia.acme.orderservice.model.domain.OrderItem;
import com.invillia.acme.orderservice.repository.IOrderItemRepository;
import com.invillia.acme.orderservice.repository.IOrderItemStatusRepository;
import com.invillia.acme.orderservice.repository.IOrderRepository;
import com.invillia.acme.orderservice.repository.IOrderStatusRepository;

@Service
@Transactional
@Validated
public class OrderService {
	
	@Autowired
	private IOrderRepository orderRepo;
	
	@Autowired
	private IOrderStatusRepository orderStatusRepo;
	
	@Autowired
	private IOrderItemStatusRepository orderItemStatusRepo;
	
	@Autowired
	private IOrderItemRepository orderItemRepo;

	@Autowired
	private Validator validator;
	
	private void validateOrderItem(Set<OrderItem> items) {
		items.forEach(i -> {
			Set<ConstraintViolation<OrderItem>> violations = validator.validate(i);
			if(!violations.isEmpty())
				throw new ConstraintViolationException(violations);
		});
	}

	@Transactional(rollbackFor = {ConstraintViolationException.class})
	public Order save(@Valid Order order) {
		validateOrderItem(order.getItems());
		
		order.setStatus(orderStatusRepo.findById(1).get());
		order.setConfirmationDate(LocalDate.now());
		if(order.getItems() != null && !order.getItems().isEmpty())
			order.getItems().forEach(i -> {
				i.setOrder(order);
				i.setStatus(orderItemStatusRepo.findById(1).get());
			});
		return orderRepo.save(order);
	}
	
	@Transactional(readOnly = true)
	public Order findById(Long id) {
		return orderRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found to id " + id));
	}
	
	@Transactional(readOnly = true)
	public Collection<Order> findAllByStatusId(Integer id) {
		return orderRepo.findAllByStatusId(id);
	}
	
	public Order payOrder(Long orderId) {
		Order order = findById(orderId);
		// Awaiting Fulfillment = payment confirmed
		order.setStatus(orderStatusRepo.findById(3).get());
		// Awaiting Shipment
		order.getItems().forEach(i -> i.setStatus(orderItemStatusRepo.findById(2).get()) );
		
		return orderRepo.save(order);
	}
	
	private void validateOrderRefund(Order order) {
		if( !order.getStatus().getId().equals(3) )
			throw new RefundException("Order just should be refunded until the payment is concluded. Order Status = " + 
					order.getStatus().getName());
		if( order.getConfirmationDate().isBefore(LocalDate.now().minusDays(10)) )
			throw new RefundException("Order just should be refunded until ten days after confirmation. Confirmation Date = " + 
					order.getConfirmationDate());
	}
	
	public Order refundOrder(Long orderId) {
		Order order = findById(orderId);
		
		validateOrderRefund(order);
		
		// refund order
		order.setStatus(orderStatusRepo.findById(4).get());
		// refund items
		order.getItems().forEach(i -> i.setStatus(orderItemStatusRepo.findById(4).get()) );
		
		return orderRepo.save(order);
	}
	
	public OrderItem refundOrderItem(Long orderItemId) {
		OrderItem orderItem = orderItemRepo.findById(orderItemId)
				.orElseThrow(() -> new EntityNotFoundException("Order Item not found to id " + orderItemId));
		
		validateOrderRefund(orderItem.getOrder());
		
		// refund
		orderItem.setStatus(orderItemStatusRepo.findById(4).get());
		return orderItemRepo.save(orderItem);
	}
	
}
