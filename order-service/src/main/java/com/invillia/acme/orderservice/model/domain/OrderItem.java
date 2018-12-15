package com.invillia.acme.orderservice.model.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Description should not be empty")
	@NotNull(message = "Description required")
	private String description;
	@Digits(integer = 10, fraction = 2)
	@NotNull(message = "Unit Price required")
	private BigDecimal unitPrice;
	@Positive(message = "Quantity must be positive")
	@NotNull(message = "Quantity required")
	private Integer quantity;
	@PositiveOrZero(message = "Position must be zero or positive")
	@NotNull(message = "Position required")
	private Integer position;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;
	@ManyToOne
	private OrderItemStatus status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderItemStatus getStatus() {
		return status;
	}
	public void setStatus(OrderItemStatus status) {
		this.status = status;
	}
	public BigDecimal getTotalPrice() {
		return unitPrice.multiply(new BigDecimal(quantity));
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof OrderItem)) {
			return false;
		}
		OrderItem castOther = (OrderItem) other;
		if(getId() == null) {
			return getPosition() != null && Objects.equals(getPosition(), castOther.getPosition());
		} else
			return getId() != null && Objects.equals(getId(), castOther.getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
	
}
