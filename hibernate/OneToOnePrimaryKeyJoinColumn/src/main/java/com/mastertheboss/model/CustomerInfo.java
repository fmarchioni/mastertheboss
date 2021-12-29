package com.mastertheboss.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
@Entity(name = "CustomerInfo")
public   class CustomerInfo  {

	@Id
	private Long id;
	private String name;


	private String address;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Customer customer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CustomerInfo [id=" + id + ", name=" + name + ", address=" + address + ", customer=" + customer + "]";
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.id = customer.getId();
	}
 
}