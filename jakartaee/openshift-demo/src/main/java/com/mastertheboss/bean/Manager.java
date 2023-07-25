package com.mastertheboss.bean;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
 
import jakarta.inject.Inject;
import jakarta.inject.Named;

 
import com.mastertheboss.ejb.ServiceBean;
import com.mastertheboss.model.SimpleProperty;
import com.mastertheboss.producer.Producer;

@Model
public class Manager {
  
	@Inject
	ServiceBean ejb;
	
	@Produces
	@Named
	SimpleProperty property;
	
	@Inject
	Producer producer;
	
        String greeting;

        public void setGreeting(String g) {
             this.greeting = g;
        }

        public String getGreeting() {
             // Taken from configuration
             return System.getProperty("greeting");
        }
	@PostConstruct
	public void initNewProperty() {
      
		property = new SimpleProperty();
	}
	 
	public void save() {
		ejb.put(property);
		System.out.println("saved "+property);
		initNewProperty();
	}

	public void clear(SimpleProperty property) {
		ejb.delete(property);
		 
	}
 

}
