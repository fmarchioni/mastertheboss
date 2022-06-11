package com.mastertheboss.jaxrs.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
    public Customer() {     }


    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @NotNull
    String id;
    @Size(min = 3, max = 15)
    String name;
    @Size(min = 3, max = 15)
    String email;
}
