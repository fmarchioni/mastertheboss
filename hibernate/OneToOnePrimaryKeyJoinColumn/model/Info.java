package com.mastertheboss.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Info implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    private String address;

    private String city;

    private String state;

     public String toString() {
        return "address; = " + address+ " - city: " +city + " - state: " +state;
    }
    /*
     *  the Child object I was adding to the Parent wasn't saved in the database yet. So when I added the Child to the Parent, then saved the Parent, Hibernate would toss the "object references an unsaved transient instance - save the transient instance before flushing" message when saving the Parent.

Adding in the cascade = {CascadeType.ALL} on the Parent's reference to the Child solved the problem in both cases. This saved the Child and the Parent.

     */
    @OneToOne(mappedBy = "info")
    private Worker worker;

    public Info() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Worker getWorker() {
        return this.worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    
}