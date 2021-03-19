package com.itbuzzpress.ejb;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
@Startup
public class UserRegistry {
 
    @Resource
	TimerService timerService;

	public ArrayList<String> listUsers;
	@PostConstruct
	public void init() {
		listUsers = new ArrayList<String>();
		listUsers.add("administrator");

		timerService.createSingleActionTimer(5000, new TimerConfig());
		 
	}
	public void addUser(String username) {
		listUsers.add(username);	
	}
	public void removeUser(String username) {
		listUsers.remove(username);	
	}

	public ArrayList<String> getListUsers() {
		return listUsers;
	}
    @Timeout
	public void createReports(javax.ejb.Timer timer){
	   System.out.println("It is time to create a Report!");	 
	}
	
    @Schedule(hour = "*", minute = "*", second = "*/60", info ="Every minute timer",persistent=true)
    public void printDate() {

        System.out.println("Schedule invoked at " + new java.util.Date().toString());
	 
    }

}
