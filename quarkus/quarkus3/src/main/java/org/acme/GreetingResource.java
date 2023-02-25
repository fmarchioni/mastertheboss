package org.acme;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.List;

@Path("/hello")
public class GreetingResource {
    @GET
    @RunOnVirtualThread
    public List<City> getPersons() {
        return Arrays.asList(new City("Buenos Aires"), new City("Córdoba"), new City("La Plata"));
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }



}
