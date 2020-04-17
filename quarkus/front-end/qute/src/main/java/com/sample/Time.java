package com.sample;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Date;

@ApplicationScoped
@Named
public class Time {
    public Date defaultTime = new Date();;
    public String message="hi!";



    public Time() {

    }

    public Date getDefaultTime() {
        return defaultTime;
    }

    public void setDefaultTime(Date defaultTime) {
        this.defaultTime = defaultTime;
    }
}
