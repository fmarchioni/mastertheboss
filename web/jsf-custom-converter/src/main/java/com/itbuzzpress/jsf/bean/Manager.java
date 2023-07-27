package com.itbuzzpress.jsf.bean;


 
import jakarta.enterprise.context.RequestScoped;
 
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
 
import jakarta.inject.Named;

 


@Named
@RequestScoped
public class Manager {
 
    private String name;
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void update() {
		 
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		 

	}

	 
}