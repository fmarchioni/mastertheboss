package com.itbuzzpress.jsf.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.itbuzzpress.jsf.model.User;
 
@Model
public class Manager {

	List<User> userList;
	
	@PostConstruct
	public void retrieveAllItems() {
		user = new User(); 
		
		userList = new ArrayList<User>();
	    userList.add(new User("John","Smith","jsmith@gmail.com"));
	    userList.add(new User("Frank","Walker","jwalker@gmail.com"));
	    userList.add(new User("Fiona","Middleton","fmiddleton@gmail.com"));
	}
	
	@Produces
	@Named
	User user;
	
	@Produces
	@Named
	public List<User> getUserList() {
		return userList;
	}

	public void save() {
		userList.add(user);
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Added user "+user.getName(), null);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		user = new User(); 
		 
	}
}