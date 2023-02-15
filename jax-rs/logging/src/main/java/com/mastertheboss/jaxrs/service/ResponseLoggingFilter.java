package com.mastertheboss.jaxrs.service;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@Log
@Provider
public class ResponseLoggingFilter implements ContainerResponseFilter {
    private static final Logger logger = Logger.getLogger(ResponseLoggingFilter.class.getName());
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        Map<String, NewCookie> cookies = responseContext.getCookies();
        if (cookies != null && !cookies.isEmpty()) {
            logger.info("Response cookies:");
            for (Map.Entry<String, NewCookie> entry : cookies.entrySet()) {
                String name = entry.getKey();
                NewCookie cookie = entry.getValue();
                logger.info(name + "=" + cookie.getValue());
            }
        }
    }
}