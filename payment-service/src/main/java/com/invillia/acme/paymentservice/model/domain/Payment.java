package com.invillia.acme.paymentservice.model.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Credit Card Number should not be empty")
	@NotNull(message = "Credit Card Number required")
	private String creditCardNumber;
	private LocalDate paymentDate;
	@ManyToOne
	private PaymentStatus status;
	@Positive(message = "Order Id must be positive")
	@NotNull(message = "Order Id required")
	private Long orderId;
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getId() {
		return id;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Payment)) {
			return false;
		}
		Payment castOther = (Payment) other;
		return getId() != null && Objects.equals(getId(), castOther.getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
}
