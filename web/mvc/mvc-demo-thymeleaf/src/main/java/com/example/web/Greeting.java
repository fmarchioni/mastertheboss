package com.example.web;
import javax.inject.Named;

import javax.enterprise.context.RequestScoped;
@Named("greeting")
@RequestScoped
public class Greeting {
    public Greeting(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
