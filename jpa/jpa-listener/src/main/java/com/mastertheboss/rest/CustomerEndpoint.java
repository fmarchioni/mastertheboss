package com.mastertheboss.rest;


import com.mastertheboss.ejb.CustomerService;
import com.mastertheboss.model.Customer;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;




@Path("/customer")
@Produces("application/json")
@Consumes("application/json")

public class CustomerEndpoint {

	@Inject
	CustomerService customerService;

	@POST
	public Response create(Customer customer) {
		customerService.createCustomer(customer);
		return Response.status(201).build();
	}

	@GET
	public List<Customer> findAllCustomers() {
		return customerService.findAllCustomers();
	}

	@GET
	@Path("/{id}")
	public Customer findCustomerById(@PathParam("id") Long id) {
		return customerService.findCustomerById(id);
	}

	@PUT
	public Response updateCustomer(Customer customer) {
		customerService.updateCustomer(customer);
		return Response.status(204).build();
	}
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		customerService.deleteCustomer(id);
		return Response.status(204).build();
	}

}
