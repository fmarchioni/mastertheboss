package com.example.telemetry;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

@Path("/restendpoint")
public class ExampleEndpoint {
	   @Inject
	    private Tracer tracer;
	   
	    @GET
	    public Response doSomeWork() {
	    	System.out.println("starting.......");
	        final Span span = tracer.spanBuilder("Doing some work")
	                .startSpan();
	        span.makeCurrent();
	        doSomeMoreWork();
	        span.addEvent("Make request to external system.");
	        doSomeMoreWork();
	        span.addEvent("All the work is done.");
	        span.end();

	        return Response.ok().build();
	}

	 

		private void doSomeMoreWork() {
			try {
				System.out.println("done");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
