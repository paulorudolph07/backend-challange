package com.invillia.acme.orderservice.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.invillia.acme.orderservice.model.domain.Order;

public interface IOrderRepository extends CrudRepository<Order, Long> {

	Collection<Order> findAllByStatusId(Integer id);
	Order findByIdAndStatusId(Long id, Integer statusId);
	
}
