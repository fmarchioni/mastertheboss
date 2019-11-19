package com.mastertheboss.rest;

import com.mastertheboss.ejb.ServiceBean;
import com.mastertheboss.model.SimpleProperty;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/service")
public class RESTService {

	@Inject
	ServiceBean ejb;

	@GET
	@Path("/list/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SimpleProperty getPropertyByPathParam(@PathParam("id") String id)
	{
		return ejb.findById(id);
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SimpleProperty> getProperty()
	{
		return ejb.findAll();
	}
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response createProperty(@FormParam("key")String key,
								   @FormParam("value")String value)
	{
		ejb.put(key,value);

		return Response.ok("Inserted! Go back and check the list.").build();

	}

}