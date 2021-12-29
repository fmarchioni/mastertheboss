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
import com.mastertheboss.model.Address;

@Stateless

public class ServiceBean {

	@PersistenceContext
	private EntityManager em;

	public String create() {
		Customer customer1 = new Customer();	 
		customer1.setName("John Smith");
		customer1.setPhoneNumber("328/1145678");
		customer1.setEmail("johnsmith@gmail.com");

		Customer customer2 = new Customer();	 
		customer2.setName("Frank Smith");
		customer2.setPhoneNumber("313/3454643");
		customer2.setEmail("franksmith@gmail.com");

		
		Address address1 = new Address();
		address1.setStreet("15th Avenue");
		address1.setCity("New York");
		
		Address address2 = new Address();
		address2.setStreet("Rue de Rivoli");
		address2.setCity("Paris");
		
		List<Address> list1 = new ArrayList();
		list1.add(address1);
		list1.add(address2);
		customer1.setAddresses(list1);

		List<Address> list2 = new ArrayList();
		list2.add(address1);
		customer2.setAddresses(list1);



		
		em.persist(customer1);
		em.persist(customer2);

		return "Created!";
	}

	public Customer findCustomer(Long i) {
		 		
		Customer c = this.em.find(Customer.class, i);
		System.out.println("Found Customer "+c);
		System.out.println("Found Address "+c.getAddresses());
		return c;

	}

}