package com.mastertheboss.jaxrs.service;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/tutorial")
public class SimpleRESTService {


	@GET
	@Path("hello")
	@RolesAllowed({"Admin"})
	public String hello() {
		return "Hello ";
	}
 
}
