package com.sample.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Property")
public class Property {
	@Override
	public String toString() {
		return "Property [key=" + key + ", value=" + value + "]";
	}

@Id 
private String key;

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
  
 
private String value;
}
