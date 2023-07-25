package com.mastertheboss.producer;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Reception;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

 
import com.mastertheboss.model.SimpleProperty;
import com.mastertheboss.repository.RepositoryManager;

 
@RequestScoped
public class Producer {
	@Inject RepositoryManager db;

	private List<SimpleProperty> propertyList;

	 
	public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SimpleProperty member) {
		retrieveAllSeatsOrderedByName();
	}
	
	@Produces
	@Named
	public List<SimpleProperty> getPropertyList() {
		return propertyList;
	}

	public void setProperty(List<SimpleProperty> property) {
		this.propertyList = propertyList;
	}

	@PostConstruct
	public void retrieveAllSeatsOrderedByName() {

		propertyList = db.queryCache();

	}
	

}
