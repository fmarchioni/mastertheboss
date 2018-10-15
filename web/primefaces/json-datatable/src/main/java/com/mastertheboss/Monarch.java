package com.mastertheboss;

public class Monarch {
    String name;
    String city;
    String house;
    String years;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Monarch(String name, String city, String house, String years) {
        super();
        this.name = name;
        this.city = city;
        this.house = house;
        this.years = years;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
