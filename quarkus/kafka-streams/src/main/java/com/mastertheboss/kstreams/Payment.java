package com.mastertheboss.kstreams;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.io.Serializable;


@RegisterForReflection
public class Payment implements Serializable {
    String email;

    public Payment(String email, int money) {
        this.email = email;
        this.money = money;
    }

    public Payment(String email) {
        this.email = email;

    }

    public Payment() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Payment [email=" + email + ", money=" + money + "]";
    }

}    
