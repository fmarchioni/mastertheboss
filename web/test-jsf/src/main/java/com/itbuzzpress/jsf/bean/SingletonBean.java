package com.itbuzzpress.jsf.bean;

import com.itbuzzpress.jsf.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;

import java.util.ArrayList;
import java.util.List;




@Singleton
public class SingletonBean {
	List<User> listUsers;

	@PostConstruct
	public void init() {
		listUsers = new ArrayList();
	}
	public void put(User user) {
		this.listUsers.add(user);

	}
	public void delete(User user) {
		listUsers.remove(user);

	}
	public List<User> getListUsers() {
		return listUsers;
	}
	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
}
