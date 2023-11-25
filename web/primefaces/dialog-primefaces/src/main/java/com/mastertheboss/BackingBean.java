package com.mastertheboss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("buttonView")
@ViewScoped
public class BackingBean implements Serializable  {
   
 

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

 
    
    public void action1() {
         
        System.out.println("Clicked Yes!"); 
        
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Clicked Yes!");
    }

    public void action2() {
         
        System.out.println("Clicked No!"); 
        
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Clicked No!");
    }
 
    
}
