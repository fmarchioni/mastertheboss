package com.sample.infinispan.client;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;

public class Customer {
    private String id;
    private String name;
    private String surname;

    @ProtoField(number = 1)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ProtoFactory
    public Customer(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @ProtoField(number = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ProtoField(number = 3)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    public Customer() {
    }
}