package com.invillia.acme.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.orderservice.model.domain.OrderStatus;

@Repository
public interface IOrderStatusRepository extends CrudRepository<OrderStatus, Integer> {

}
