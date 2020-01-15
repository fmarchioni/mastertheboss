package com.mastertheboss.camel;

public class GreetingBean {

    private String hi;


    public GreetingBean(String hi) {
        this.hi = hi;
    }

    public String hello() {
        return hi + " how are you?";
    }


}