package com.mastertheboss.jaxrs.model;

import jakarta.xml.bind.annotation.XmlRootElement;





@XmlRootElement
public class Person {
	public Person() {     }


	public Person(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String name;
	String email;
	 
}