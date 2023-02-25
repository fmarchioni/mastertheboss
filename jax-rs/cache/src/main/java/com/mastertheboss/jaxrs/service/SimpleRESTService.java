package com.mastertheboss.jaxrs.service;


import com.mastertheboss.jaxrs.model.Customer;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import org.jboss.resteasy.annotations.cache.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@Path("/api")


//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)

public class SimpleRESTService {

	@GET
	@Path("/cache")
	@Cache(maxAge = 3600, sMaxAge = 1800, mustRevalidate = true, proxyRevalidate = true)
	public Response getCustomerCache() {
		Customer c = new Customer("John Doe");
		return Response.ok(c).build();
	}

	@GET
	@Path("/nocache")
	@NoCache
	public Response getCustomerNoCache() {
		Customer c = new Customer("John Doe");
		return Response.ok(c).build();
	}
}
