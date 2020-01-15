package com.mastertheboss.camel;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

public class MyDSLRouteBuilder extends EndpointRouteBuilder {
    public void configure() {

        from(quartz("foo?cron={{myCron}}")).
                bean("greetingBean", "hello").process((exchange) -> {
            exchange.getIn().setBody(exchange.getIn().getBody(String.class).toUpperCase());
        }).log("${body}");
    }
}
