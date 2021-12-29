package com.mastertheboss.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@SuppressWarnings("serial")
@Entity

public class Address implements Serializable {
 
 
 
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + "]";
	}
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
    @Column
    private String street;
    
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
	@Column
    private String city;
    
    //bi-directional many-to-one association to Customer
    @ManyToMany(mappedBy = "addresses")
	private List<Customer> customers = new ArrayList();

    public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
 
 
}