package com.mastertheboss.jaxrs.service;




import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/")
public class SimpleRESTService {


	@GET
	public String hello()
	{
		return "Hello from Bootable JAR!\n ";
	}


}
