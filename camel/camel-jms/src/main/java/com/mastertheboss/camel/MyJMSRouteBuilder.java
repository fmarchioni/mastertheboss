package com.mastertheboss.camel;

import org.apache.camel.builder.RouteBuilder;

public class MyJMSRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo?period=3s")
                .transform(constant("Hello World"))
                .to("jms:queue:demoQueue");

        from("jms:queue:demoQueue").to("log:demoQueue");
    }
}