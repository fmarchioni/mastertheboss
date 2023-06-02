package com.sample;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloServiceConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://foo?repeatCount=1")
        .setBody(simple("Frank")) // Set the request payload
            .to("cxf:http://localhost:8080/services/HelloService?serviceClass=com.sample.HelloService")
            .log("Response: ${body}");
    }
}
