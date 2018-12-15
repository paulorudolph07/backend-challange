package com.invillia.acme.providerservice.model.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="name", name="uk_provider_name")})
public class Provider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Name should not be empty")
	@NotNull(message = "Name required")
	private String name;
	@NotEmpty(message = "Address should not be empty")
	@NotNull(message = "Address required")
	private String address;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Provider)) {
			return false;
		}
		Provider castOther = (Provider) other;
		return getId() != null && Objects.equals(getId(), castOther.getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
	
}
