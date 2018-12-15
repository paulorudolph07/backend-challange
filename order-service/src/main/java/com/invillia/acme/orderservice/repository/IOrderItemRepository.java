package com.invillia.acme.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.orderservice.model.domain.OrderItem;

@Repository
public interface IOrderItemRepository extends CrudRepository<OrderItem, Long> {

}
