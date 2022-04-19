package com.mastertheboss.quarkus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")
@Entity
public class Ticket  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	public String name;

	@Column
	public String seat;

	public Ticket() {
	}

	public Ticket(String name, String seat) {
		this.name = name;
		this.seat = seat;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", name=" + name + ", seat=" + seat + "]";
	}

}