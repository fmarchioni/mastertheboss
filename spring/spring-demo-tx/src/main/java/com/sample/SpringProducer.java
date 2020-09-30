package com.sample;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@ApplicationScoped
public class SpringProducer {
    @Produces
    public ConfigurableApplicationContext create() {
        return new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public void close(@Disposes final ConfigurableApplicationContext ctx) {
        ctx.close();
    }
}