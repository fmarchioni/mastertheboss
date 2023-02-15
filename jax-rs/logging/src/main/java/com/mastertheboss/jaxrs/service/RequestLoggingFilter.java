package com.mastertheboss.jaxrs.service;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Logger;

@Provider
@Log
public class RequestLoggingFilter implements ContainerRequestFilter {
    private static final Logger logger = Logger.getLogger(RequestLoggingFilter.class.getName());


    @Override
    public void filter(ContainerRequestContext crc) {
        System.out.println(crc.getMethod() + " " + crc.getUriInfo().getAbsolutePath());
        for (String key : crc.getHeaders().keySet()) {
            logger.info("[REST Logging] " +key + ": " + crc.getHeaders().get(key));
        }
    }
}