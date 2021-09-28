package com.example.telemetry;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
