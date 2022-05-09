package org.acme;

public class Hero {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public Hero(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	String name;
String surname;
}
