package com.mastertheboss.reactive;


public class Quote   {
    String company;
    Double value;


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Quote(String company, Double value) {
        this.company = company;
        this.value = value;
    }

    public Quote() {
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
