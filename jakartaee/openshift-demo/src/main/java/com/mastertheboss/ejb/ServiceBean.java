package com.mastertheboss.ejb;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

 
 
import com.mastertheboss.model.SimpleProperty;



@Stateless

public class  ServiceBean   {

	@Inject
	private Event<SimpleProperty> propEventSrc;
	
	@Inject
	private EntityManager em;

	
	public void put(SimpleProperty p){
		 
	      em.persist(p);
	      propEventSrc.fire(p);
	}
	 
	public void delete(SimpleProperty p){

		Query query = em.createQuery("delete FROM SimpleProperty p where p.key='"+p.getKey()+"'");

		query.executeUpdate();
		propEventSrc.fire(p);
		 	      
	}



}
