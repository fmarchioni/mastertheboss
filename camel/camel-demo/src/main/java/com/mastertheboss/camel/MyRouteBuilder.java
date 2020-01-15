package com.mastertheboss.camel;

import org.apache.camel.builder.RouteBuilder;


public class MyRouteBuilder extends RouteBuilder {


    public void configure() {
        from("quartz:foo?cron={{myCron}}").bean("greetingBean", "hello").process((exchange) -> {
            exchange.getIn().setBody(exchange.getIn().getBody(String.class).toUpperCase());
        }).log("${body}");

    }

}
