package com.mastertheboss.rest;

import com.mastertheboss.ejb.ServiceBean;
import com.mastertheboss.model.Person;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("/service")
public class RESTService {

	@Inject
	ServiceBean service;

	@GET
	@Path("/list/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getById(@PathParam("id") String id)
	{
		return service.findById(id);
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getAll()
	{
		return service.findAll();
	}
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response createPerson(@FormParam("name")String name,
								   @FormParam("surname")String surname)
	{
		service.add(name,surname);

		return Response.ok("Inserted! Go back and check the list.").build();

	}

}