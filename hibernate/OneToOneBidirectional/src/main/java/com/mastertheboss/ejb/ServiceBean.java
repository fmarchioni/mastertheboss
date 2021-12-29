package com.mastertheboss.ejb;

import java.util.ArrayList;
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
import com.mastertheboss.model.CustomerInfo;
 



@Stateless

public class  ServiceBean   {

	@PersistenceContext
	private EntityManager em;

	public String create(){
		Customer customer = new Customer( UUID.randomUUID().toString());
		

		CustomerInfo info = new CustomerInfo();
		info.setName("John Smith");
		info.setAddress("15th Avenue New York" );
		info.setCustomer(customer );
		em.persist( customer );
		em.persist(info);
	return "Created!";
	}
 
 
	public List<CustomerInfo> findAll(){

		Query query = em.createQuery("FROM CustomerInfo");
		List <CustomerInfo> list = query.getResultList();
		return list;

	}

 
}