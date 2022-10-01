package com.itbuzzpress.jaxrs.bootstrap;

import jakarta.ws.rs.SeBootstrap;


public class Main {

    public static void main(final String[] args) throws Exception {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
        SeBootstrap.Configuration configuration = SeBootstrap.Configuration.builder()
                .host("localhost")
                .port(8081)
                .protocol("http")
                .build();

        SeBootstrap.start(RestActivator.class,configuration)
                .thenAccept(instance -> {
                    instance.stopOnShutdown(stopResult -> System.out.printf("Stopped container (%s)", stopResult.unwrap(Object.class)));
                    log("REST Server running at %s",
                            instance.configuration().baseUri());
                    log("Example: %s",
                            instance.configuration().baseUriBuilder().path("rest/frank").build());
                    log("Send SIGKILL to shutdown container");
                });
        Thread.currentThread().join();
    }

    private static void log(final String fmt, final Object... args) {

            System.out.printf(fmt, args);
            System.out.println();

    }

}
