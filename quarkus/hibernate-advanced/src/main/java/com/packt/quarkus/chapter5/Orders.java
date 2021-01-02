package com.packt.quarkus.chapter5;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
@Entity
@NamedQuery(name = "Orders.findAll",
        query = "SELECT o FROM Orders o WHERE o.customer.id = :customerId ORDER BY o.item")
public class Orders {
    @Id
    @SequenceGenerator(
            name = "orderSequence",
            sequenceName = "orderId_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
    public Long id;

    @Column(length = 40)
    public String item;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", price=" + price +
                '}';
    }

    @Column
    public Long price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonbTransient
    public Customer customer;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
