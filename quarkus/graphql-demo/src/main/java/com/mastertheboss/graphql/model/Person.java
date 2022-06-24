package com.mastertheboss.graphql.model;

public class Person {

	String name;
	Country country;
	
	public Person() {
		super();
		 
	}
	public Person(String name, Country country) {
		this.name=name;
		this.country=country;
	 
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", country=" + country + "]";
	}
	 
}