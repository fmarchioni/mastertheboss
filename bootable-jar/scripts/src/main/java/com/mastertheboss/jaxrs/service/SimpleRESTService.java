package com.mastertheboss.jaxrs.service;


 


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/")
public class SimpleRESTService {


	@GET
	@Path("property/{name}")
	public String helloProperty(@PathParam("name") final String name) {
		return "Hello " +System.getProperty(name);
	}

	 
}
