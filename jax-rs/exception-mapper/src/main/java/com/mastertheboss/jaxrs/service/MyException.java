package com.mastertheboss.jaxrs.service;

import javax.ws.rs.WebApplicationException;

public class MyException extends WebApplicationException {

	public MyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyException(String message) {
		super("MyException Error: " +message);
		// TODO Auto-generated constructor stub
	}

}
