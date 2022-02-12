package org.acme.remote.service

import io.undertow.Undertow;
import io.undertow.util.Headers;

public class UndertowServer {
    public static void main(String[] args) {
        Undertow server1 = Undertow.builder().addHttpListener(8090, 
          "localhost").setHandler(exchange -> {
            exchange.getResponseHeaders()
            .put(Headers.CONTENT_TYPE, "text/plain");
          exchange.getResponseSender().send("Hello Service 1");
        }).build();
        server1.start();
        
        Undertow server2 = Undertow.builder().addHttpListener(8091, 
                "localhost").setHandler(exchange -> {
                  exchange.getResponseHeaders()
                  .put(Headers.CONTENT_TYPE, "text/plain");
                exchange.getResponseSender().send("Hello Service 2");
              }).build();
        server2.start();
              
    }
}