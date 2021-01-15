package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.FormParam;
import java.io.Serializable;

@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FormParam("name")
    @NotBlank(message = "Name is empty")
    @Column
    private String name;



    @FormParam("address")
    @NotBlank(message = "Address is empty")
   // @Size(min = 10, max = 2000)
    @Column
    private String address;

    @FormParam("email")
    @NotBlank(message = "Address is empty")
    @Column
    private String email;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
