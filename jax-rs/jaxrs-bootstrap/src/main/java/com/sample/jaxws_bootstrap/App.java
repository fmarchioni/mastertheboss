package com.sample.jaxws_bootstrap;

 
import java.net.URI;

import jakarta.ws.rs.SeBootstrap;
import jakarta.ws.rs.core.Application;
public class App {

	 public static void main(final String[] args) throws InterruptedException {
	        final Application application = new HelloWorld();

	    	SeBootstrap.Configuration config = SeBootstrap.Configuration.builder().port(8080).build();

	        final SeBootstrap.Configuration requestedConfiguration = SeBootstrap.Configuration.builder().from(config)
	                .build();

	        SeBootstrap.start(application, requestedConfiguration).thenAccept(instance -> {
	            instance.stopOnShutdown(stopResult ->
	                    System.out.printf("Stop result: %s [Native stop result: %s].%n", stopResult,
	                            stopResult.unwrap(Object.class)));
	            final URI uri = instance.configuration().baseUri();
	            System.out.printf("Instance %s running at %s [Native handle: %s].%n", instance, uri,
	                    instance.unwrap(Object.class));
	            System.out.println("Send SIGKILL to shutdown.");
	        });

	        Thread.currentThread().join();
	    }
}