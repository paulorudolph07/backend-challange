package com.invillia.acme.providerservice.model.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
public class ProviderAddress {

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
	
	public ProviderAddress() {
		super();
	}
	
	public ProviderAddress(String street, String city, String state, String zipCode, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
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
}
