package com.mastertheboss.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "Customer")
public class Customer  {

	@Override
	public String toString() {
		return "Customer [id=" + id + ", registrationNumber=" + registrationNumber + "]";
	}

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(
			mappedBy = "customer",
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
	private CustomerInfo info;
	
	public CustomerInfo getInfo() {
		return info;
	}

	public void setInfo(CustomerInfo info) {
		this.info = info;
	}

	private String registrationNumber;

	public Customer() {}

	public Customer(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

}