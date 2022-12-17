package com.itbuzzpress.jsf.bean;


import com.itbuzzpress.jsf.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;



@Model
public class Manager {

	@Inject SingletonBean ejb;

	List<User> userList;

	@PostConstruct
	public void retrieveAllItems() {
		user = new User();
		userList = new ArrayList<User>();

	}

	@Produces
	@Named
	User user;

	@Produces
	@Named
	public List<User> getUserList() {
		return ejb.getListUsers();
	}


	public void save() {
		System.out.println("Saving user "+user);
		ejb.put(user);
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Added user "+user.getName(), null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		user = new User();

	}

	public void delete(User user) {
		ejb.delete(user);

	}


	public boolean isFull() {
		return (ejb.getListUsers().size() > 0);

	}
}