package com.sample.bean;

import com.sample.ejb.PropertyManager;
import com.sample.model.Property;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;

import javax.inject.Inject;

@Model
public class Controller {

    @Inject
    PropertyManager ejb;

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    List<Property> propertyList;

    private String key;
    private String value;

    @PostConstruct
    public void readDB() {
        propertyList = ejb.queryCache();

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void save() {
        Property p = new Property();
        p.setKey(key);
        p.setValue(value);
        ejb.save(p);
        propertyList.add(p);
        key="";
        value="";
    }

}
