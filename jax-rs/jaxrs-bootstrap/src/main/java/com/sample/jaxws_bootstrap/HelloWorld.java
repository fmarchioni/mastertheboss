package com.sample.jaxws_bootstrap;

import java.util.Collections;
import java.util.Set;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;


@Path("greet")
public class HelloWorld extends Application {

    /**
     * Returns the sole, singleton resource.
     */
    @Override
    public Set<Class<?>> getClasses() {
        return Collections.singleton(HelloWorld.class);
    }

    /**
     * @return {@code "Hello, World!"}
     */
    @GET
    public String greeting() {
        return "Hello World!";
    }

}