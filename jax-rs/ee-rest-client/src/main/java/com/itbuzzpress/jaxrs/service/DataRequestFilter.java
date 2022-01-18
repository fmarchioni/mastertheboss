package com.itbuzzpress.jaxrs.service;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
 

//This filter adds a body to Data field
public class DataRequestFilter implements ClientRequestFilter {

 

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        String method = requestContext.getMethod();
        if ("POST".equals(method) && requestContext.hasEntity()) {
            Data mydata = (Data) requestContext.getEntity();
            mydata.setBody("Default body");
            requestContext.setEntity(mydata);
        }

    }

}