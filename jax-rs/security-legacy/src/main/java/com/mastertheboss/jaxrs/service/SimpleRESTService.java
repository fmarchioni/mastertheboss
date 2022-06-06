package com.mastertheboss.jaxrs.service;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/tutorial")
public class SimpleRESTService {


	@GET
	@Path("hello")
	@RolesAllowed({"Admin"})
	public String hello() {
		return "Hello ";
	}
 
}
