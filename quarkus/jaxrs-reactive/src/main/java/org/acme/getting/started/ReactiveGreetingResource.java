package org.acme.getting.started;

import java.time.Duration;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestSseElementType;

@Path("/hello")
public class ReactiveGreetingResource {

 

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public Uni<String> greeting(String name) {
    	 return Uni.createFrom().item(name)
         .onItem().transform(n -> String.format("hello %s", name));
    }
 

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.TEXT_PLAIN)
    @Path("/stream/{count}/{name}")
    public Multi<String> greetingsAsStream(int count, String name) {
    	 return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                 .onItem().transform(n -> String.format("hello %s - %d", name, n))
                 .transform().byTakingFirstItems(count);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public  Uni<String> hello() {
    	return Uni.createFrom().item("hello");
    }
}
