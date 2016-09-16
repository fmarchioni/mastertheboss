package com.mastertheboss.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostRemove;

@Entity
public class SimpleProperty {
 
	
	@Id 
	@Column(name="id")
	private String key;

	private String value;
	
	private LocalDateTime timestamp;
	private LocalDate date;
	

 

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

 
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

 

    

}
