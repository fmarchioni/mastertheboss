package com.acme.example;


import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;
import org.jboss.msc.service.Service;

public class SimpleService implements Service<SimpleService> {

    public static final ServiceName NAME = ServiceName.of("test", "service", "simple");

    @Override
    public SimpleService getValue() throws IllegalStateException, IllegalArgumentException {
        return this;
    }

    @Override
    public void start(StartContext context) throws StartException {
        System.out.println("Started Service!");
    }

    @Override
    public void stop(StopContext context) {
        System.out.println("Stopped Service!");
    }

}

