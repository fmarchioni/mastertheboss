package org.acme;

import javax.persistence.*;

@Entity
@Table
@NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customer c ORDER BY c.name")
public class Customer {

    @Id
    @SequenceGenerator(name = "customersSequence", sequenceName = "known_customers_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "customersSequence")
    private Integer id;

    @Column(length = 40, unique = true)
    private String name;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{" + id + "," + name + '}';
    }
}