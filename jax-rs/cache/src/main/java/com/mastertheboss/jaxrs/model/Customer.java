package com.mastertheboss.jaxrs.model;

@XmlRootElement
public class Customer {
	public Customer() {     }


	private String name;

	public Customer(String name) {
		this.name=name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}