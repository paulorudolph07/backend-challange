package com.invillia.acme.providerservice.model.domain;

import java.util.Objects;

import javax.persistence.Embedded;
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
	@Embedded
	@NotNull(message = "Address required")
	private ProviderAddress address;
	
	public Provider() {
		super();
	}
	
	public Provider(String name, ProviderAddress address) {
		this.name = name;
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProviderAddress getAddress() {
		return address;
	}
	public void setAddress(ProviderAddress address) {
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
