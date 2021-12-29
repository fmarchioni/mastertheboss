package com.mastertheboss.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity(name = "CustomerInfo")
public   class CustomerInfo  {

	@Id
	@GeneratedValue
	private Long id;
	private String name;


	private String address;

	 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

 

	@Override
	public String toString() {
		return "CustomerInfo [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

 
 
}