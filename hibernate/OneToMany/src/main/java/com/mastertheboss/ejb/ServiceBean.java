package com.mastertheboss.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 
import com.mastertheboss.model.Customer;
import com.mastertheboss.model.Request;
 



@Stateless

public class  ServiceBean   {

	@PersistenceContext
	private EntityManager em;

	public String create(){
		Customer customer = new Customer();
		customer.setAddress("15th Avenue New York" );
		customer.setName("John Smith");
		customer.setPhoneNumber("328/1145678");
		customer.setEmail("johnsmith@gmail.com");
		
		Request request = new Request();
		request.setCustomer(customer);
		request.setQuantity(125);
		
		List<Request> list = new ArrayList();
		list.add(request);
	
		
		customer.setRequests(list);
		em.persist( customer );
		//em.persist(request);
		
	return "Created!";
	}
 
 
	public List <Request> findAll(){

		Query query = em.createQuery("FROM Request");
		List <Request> list = query.getResultList();
		return list;
	

	}

 
}