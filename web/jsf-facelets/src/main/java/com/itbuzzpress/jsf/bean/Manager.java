package com.itbuzzpress.jsf.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
 
import javax.inject.Named;

import com.itbuzzpress.jsf.model.User;
 
@Model
public class Manager {

	List<User> userList;
	
	@PostConstruct
	public void retrieveAllItems() {
 
		userList = new ArrayList<User>();
	    userList.add(new User("John","Smith","jsmith@gmail.com"));
	    userList.add(new User("Frank","Walker","jwalker@gmail.com"));
	    userList.add(new User("Fiona","Middleton","fmiddleton@gmail.com"));
	}
	

	@Produces
	@Named
	public List<User> getUserList() {
		return userList;
	}


}