package com.invillia.acme.paymentservice.model.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="name", name="uk_payment_status_name")})
public class PaymentStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotEmpty(message = "Name required")
	private String name;
	@NotEmpty(message = "Description required")
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof PaymentStatus)) {
			return false;
		}
		PaymentStatus castOther = (PaymentStatus) other;
		return getId() != null && Objects.equals(getId(), castOther.getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
}
