package com.mastertheboss.jaxrs.service;



import com.mastertheboss.jaxrs.model.SimpleProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/hello")
public class HelloService {
	@GET
	@Path("/text")
	public String getHello () 
	{
		return "hello world!";
	} 
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public SimpleProperty getPropertyJSON ()
	{
        SimpleProperty p = new SimpleProperty("key","value");
		return p;
	}
	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public SimpleProperty getPropertyXML () 
	{
        SimpleProperty p = new SimpleProperty("key","value");
		return p;
	}
}
