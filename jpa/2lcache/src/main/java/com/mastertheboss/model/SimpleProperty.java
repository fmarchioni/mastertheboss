package com.mastertheboss.model;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PostRemove;
import jakarta.persistence.QueryHint;

@Entity
@Cacheable
@NamedQueries(
{
@NamedQuery(
name = "findAll",
query = "SELECT  p FROM SimpleProperty p",
hints = { @QueryHint(name = "org.hibernate.cacheable", value =
"true") }  
)
})
public class SimpleProperty {
 
	
	@Id 
	@Column(name="id")
	private String key;

	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "SimpleProperty [key=" + key + ", value=" + value + "]";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

    

}
