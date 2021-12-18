package com.mastertheboss.jaxrs.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/hello")
public class SimpleRESTService {


	@GET
	public String hello(@QueryParam("id") final Integer id) {
		if (id == null) throw new MyException("You need to pass an Id!");
		
		return "Hello " +id;
	}

 
}
