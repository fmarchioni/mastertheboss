package com.mastertheboss.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String model;
	private Double price;
	
	// @JsonbDateFormat(value = "MM/dd/yyyy")
	private Date dateProd;
	
	//@jakarta.json.bind.annotation.JsonbDateFormat("dd-MM-yyyy'T'HH:mm:ss")	
	private Timestamp timeProd;  

 
	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", price=" + price + ", dateProd=" + dateProd + ", timeProd="
				+ timeProd + "]";
	}
	public Date getDateProd() {
		return dateProd;
	}
	public void setDateProd(Date dateProd) {
		this.dateProd = dateProd;
	}
	public Timestamp getTimeProd() {
		return timeProd;
	}
	public void setTimeProd(Timestamp timeProd) {
		this.timeProd = timeProd;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
 
}