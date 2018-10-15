package com.mastertheboss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "jsonView")
@ViewScoped

public class MyJsonView implements Serializable {

    private List monarchList = new ArrayList<>();

    @ManagedProperty("#{jsonService}")
    private MyJsonService service;

    @PostConstruct
    public void init() {
        try {
            monarchList = service.getData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List getMonarchList() {
        return monarchList;
    }

    public void setMonarchList(ArrayList list) {
        this.monarchList = list;
    }

    public void setService(MyJsonService service) {
        this.service = service;
    }
}
