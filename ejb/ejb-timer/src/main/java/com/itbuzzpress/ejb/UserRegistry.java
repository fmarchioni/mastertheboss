package com.itbuzzpress.ejb;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;

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
	public void createReports(jakarta.ejb.Timer timer){
	   System.out.println("It is time to create a Report!");	 
	}
	
    @Schedule(hour = "*", minute = "*", second = "*/60", info ="Every minute timer",persistent=true)
    public void printDate() {

        System.out.println("Schedule invoked at " + new java.util.Date().toString());
	 
    }

}
