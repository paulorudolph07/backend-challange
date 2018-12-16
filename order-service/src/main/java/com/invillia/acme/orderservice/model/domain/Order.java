package com.invillia.acme.orderservice.model.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "acme_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate confirmationDate;
	@ManyToOne
	private OrderStatus status;
	@Embedded
	@NotNull(message = "Address required")
	private OrderAddress address;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = false)
	private Set<OrderItem> items;
	
	public Order() {
		super();
	}
	
	public Order(OrderAddress address) {
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}
	public LocalDate getConfirmationDate() {
		return confirmationDate;
	}
	public void setConfirmationDate(LocalDate confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	public BigDecimal getTotalPrice() {
		if(items != null && !items.isEmpty())
			return items.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
		return BigDecimal.ZERO;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public OrderAddress getAddress() {
		return address;
	}
	public void setAddress(OrderAddress address) {
		this.address = address;
	}
	public Set<OrderItem> getItems() {
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Order)) {
			return false;
		}
		Order castOther = (Order) other;
		return getId() != null && Objects.equals(getId(), castOther.getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
}
