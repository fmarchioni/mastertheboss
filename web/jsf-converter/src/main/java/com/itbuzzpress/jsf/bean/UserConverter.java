package com.itbuzzpress.jsf.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter("myConverter")
 
public class UserConverter implements Converter  {

    HashMap<Integer, User> map = new HashMap<>() {{
        put(1, new User(1, "John Smith"));
        put(2, new User(2, "Frank Doe"));
    }};
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
     
        try {
            Integer intValue = Integer.parseInt(value);
            
            return map.get(intValue);
        } catch (NumberFormatException e) {
            throw new ConverterException(
                new FacesMessage("Invalid ID"), e);
        }
        
       
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        System.out.println("getAsString "+value);
        if (value == null) {
            throw new ConverterException(
                new FacesMessage("ID not found"));
        }
       
        return value.toString();
    }
}