package com.sample;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {
	@ConfigProperty(name = "greeting")
	String greeting;
	
	@Inject
	GreetingService greetingService;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
    	greetingService.saveGreeting();
        return greeting;
    }
}