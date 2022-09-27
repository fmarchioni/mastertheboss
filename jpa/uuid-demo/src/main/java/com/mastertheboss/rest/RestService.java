package com.mastertheboss.rest;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mastertheboss.ejb.ServiceBean;
import com.mastertheboss.model.Car;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/carservice")
@RequestScoped
public class RestService {

	@Inject
	private ServiceBean ejb;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> listAllCars() {
		return ejb.findAll();
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car findById(@PathParam("id") String id) {

		Car p = ejb.findById(UUID.fromString(id));
		if (p == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return p;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCar(Car car) {

		Response.ResponseBuilder builder = null;

		try {

			ejb.save(car);
			// Create an "ok" response
			builder = Response.ok("Saved!");

		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}


}