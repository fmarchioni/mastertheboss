package com.mastertheboss.jaxrs.service;





import com.mastertheboss.jaxrs.model.Customer;
import com.mastertheboss.jaxrs.model.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;



@Path("/person")
public class SimpleRESTService {

	@POST
	@Path("/savePerson")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public Response savePerson(@NotNull @FormParam("id") String id,
							@Size(min = 3, max = 15) @FormParam("name") String name,
							@Size(min = 3, max = 15) @FormParam("company") String company) {

		Person p = new Person(id, name, company);
		return Response.ok(p).build();
	}


	@POST
	@Path("/saveCustomer")
	@Consumes("application/json")
	@Produces("application/json")
	public Response saveCustomer(@Valid Customer customer) {
		return Response.ok(customer).build();
	}

}
