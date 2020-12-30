package com.mastertheboss.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@NamedQuery(name = "findAllCustomer", query = "SELECT c FROM Customer c")
public class Customer implements Serializable {

    @Id
    @SequenceGenerator(
            name = "customerSequence",
            sequenceName = "customerId_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence")
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Customer() {}

    public Customer(String name) {
        super();
        this.name = name;
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
}
