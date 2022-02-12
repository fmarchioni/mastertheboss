package org.acme;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

 
@Path("/api")
public class FrontendApi {

    @RestClient GreetingService service;
    
    @Inject
    Template api;
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance invoke() {
    	 return api.data("greeting",service.get());
         
    }

}
