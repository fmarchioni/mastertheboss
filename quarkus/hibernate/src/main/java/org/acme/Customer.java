package org.acme;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customer c ORDER BY c.name")
public class Customer {
    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence")
    @SequenceGenerator(name = "customerSequence", sequenceName = "customerSeq", allocationSize = 1, initialValue = 10)
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}