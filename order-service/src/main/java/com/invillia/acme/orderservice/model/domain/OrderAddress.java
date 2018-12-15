package com.invillia.acme.orderservice.model.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class OrderAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Street should not be empty")
	@NotNull(message = "Street required")
	private String street;
	@NotEmpty(message="City should not be empty")
	@NotNull(message = "City required")
	private String city;
	@NotEmpty(message="State should not be empty")
	@NotNull(message = "State required")
	private String state;
	@NotEmpty(message="ZipCode should not be empty")
	@NotNull(message = "ZipCode required")
	private String zipCode;
	@NotEmpty(message="Contry should not be empty")
	@NotNull(message = "Contry required")
	private String country;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof OrderAddress)) {
			return false;
		}
		OrderAddress castOther = (OrderAddress) other;
		return getId() != null && Objects.equals(getId(), castOther.getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
}
