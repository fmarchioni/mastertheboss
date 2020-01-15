package com.mastertheboss.camel;

import org.apache.camel.BindToRegistry;
import org.apache.camel.PropertyInject;

public class CustomConfiguration {
    @BindToRegistry
    public GreetingBean greetingBean(@PropertyInject("hi") String hi) {
        // this will create an instance of this bean with the name of the method (eg myBean)
        return new GreetingBean(hi);
    }

    public void configure() {
        // this method is optional and can be removed if no additional configuration is needed.
    }
}
