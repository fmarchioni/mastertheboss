package com.mastertheboss;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Ticket  {

    @Id
    @GeneratedValue()
    Long id;

    @Column(length = 20)
    public String name;

    @Column(length = 3)
    public String seat;

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", name=" + name + ", seat=" + seat + "]";
    }

    
}