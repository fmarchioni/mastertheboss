package com.mastertheboss.quarkus.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Ticket extends PanacheEntity {

    @Column(length = 20, unique = true)
    public String name;

    @Column(length = 3, unique = true)
    public String seat;

    public Ticket() {
    }

    public Ticket(String name, String seat) {
        this.name = name;
        this.seat = seat;
    }
}