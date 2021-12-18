package com.mastertheboss.jaxrs.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class MyExceptionMapper  implements ExceptionMapper<MyException> {
	 @Override
	    public Response toResponse(MyException exception) 
	    {
		    System.out.println(exception);
	        return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();  
	    }
}
