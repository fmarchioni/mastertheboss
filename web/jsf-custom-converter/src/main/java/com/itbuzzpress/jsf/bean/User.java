package com.itbuzzpress.jsf.bean;


import java.io.Serializable;

public class User implements Serializable {


	 

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String name;

	 

	 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

 


}
