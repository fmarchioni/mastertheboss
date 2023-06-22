package com.mastertheboss.ejb;


import com.mastertheboss.model.Customer;
import javax.enterprise.inject.Model;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

@Model
public class CustomerService {
	private static final Logger LOGGER = Logger.getLogger(CustomerService.class.getName());

	@PersistenceContext
	EntityManager em;

	@Transactional
	public void createCustomer(Customer customer) {
		em.persist(customer);
		LOGGER.info("Created Customer "+customer);

	}
    public List<Customer> search() {
		SearchSession searchSession = Search.session(em);
		SearchResult<Customer> result = searchSession.search( Customer.class ) 
        .where( f -> f.match() 
                .fields( "name" )
                .matching( "Jane Smith" ) )
        .fetch( 20 ); 

long totalHitCount = result.total().hitCount(); 
List<Customer> hits = result.hits();
return hits;
	}
	

	@Transactional
	public void updateCustomer( Customer customer ) {
		Customer customerToUpdate = findCustomerById(customer.getId());
		customerToUpdate.setName(customer.getName());
		customerToUpdate.setEmail(customer.getEmail());
		customerToUpdate.setAddress(customer.getAddress());
		customerToUpdate.setPhoneNumber(customer.getPhoneNumber());

		LOGGER.info("Updated customer" + customer);
	}


	@Transactional
	public void deleteCustomer(Long customerId) {
		Customer c = findCustomerById(customerId);
		em.remove(c);
		LOGGER.info("Deleted Customer with id" + customerId);
	}


	public Customer findCustomerById(Long id) {
		Customer customer = em.find(Customer.class, id);
		if (customer == null) {
			throw new WebApplicationException("Customer with id of " + id + " does not exist.", 404);
		}
		return customer;
	}


	public List<Customer> findAllCustomers() {
		Query query = em.createQuery("SELECT c FROM Customer c");
		List<Customer> customerList = query.getResultList();
		return customerList;
	}



	public Customer findCustomerByName(String name) {
		Query query = em
				.createQuery("SELECT c FROM Customer c WHERE c.name = :name");
		query.setParameter("name", name);
		Customer customer = (Customer) query.getSingleResult();
		return customer;
	}

}
