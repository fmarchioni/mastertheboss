package com.mastertheboss.bean;


import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Reception;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
 
import jakarta.inject.Inject;
import jakarta.inject.Named;

 
 
import com.mastertheboss.model.SimpleProperty;
 
import com.mastertheboss.repository.RepositoryManager;

@Model
public class Manager {
  
    @Inject RepositoryManager ejb;
	
	@Produces
	@Named
	SimpleProperty property;
	
	private List<SimpleProperty> propertyList;
	
	@Produces
	@Named
	public List<SimpleProperty> getPropertyList() {
		return propertyList;
	}

	public void setProperty(List<SimpleProperty> property) {
		this.propertyList = propertyList;
	}
	
 
	
	private String filter;
	
	
	public String getFilter() {
		return filter;
	}



	public void setFilter(String filter) {
		this.filter = filter;
	}



	@PostConstruct
	public void initNewProperty() {
		property = new SimpleProperty();
	}
	 
	public void search() {
		if (filter == null || filter.trim().length() == 0) {
			propertyList = ejb.queryCache();
		}	
		else {
		    propertyList = ejb.queryByValue(filter);
		}
	}

	public void save() {
		ejb.put(property);
		propertyList = ejb.queryCache();
		initNewProperty();
	}
	public void clear(SimpleProperty property) {
		ejb.delete(property);
		propertyList = ejb.queryCache(); 
	}
 

}
