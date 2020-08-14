package com.sample;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;

import javax.inject.Inject;

public class CDIExample {

    @Inject
    @KSession
    KieSession session;

    public boolean go(Server server) {
        session.insert(server);
        session.fireAllRules();
        return server.isValid();
    }

    public static void main(String[] args) {

        Weld w = new Weld();
        WeldContainer wc = w.initialize();

        com.sample.CDIExample bean = wc.instance().select(com.sample.CDIExample.class).get();


        Server s1 = new Server("rhel7",2,2048,1024);

        boolean isValid = bean.go(s1);
        System.out.println("Configuration isValid "+isValid);
        w.shutdown();
    }

}