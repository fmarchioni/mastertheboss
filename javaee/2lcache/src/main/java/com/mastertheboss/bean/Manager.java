package com.mastertheboss.bean;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
 
import javax.inject.Inject;
import javax.inject.Named;

 
 
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
