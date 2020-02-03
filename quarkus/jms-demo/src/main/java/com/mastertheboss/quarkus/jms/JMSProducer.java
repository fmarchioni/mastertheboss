package com.mastertheboss.quarkus.jms;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Session;


@ApplicationScoped
public class JMSProducer {

    @Inject
    ConnectionFactory connectionFactory;

    public void sendMessage(String message) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)){
            context.createProducer().send(context.createQueue("exampleQueue"), message);
        } catch (JMSRuntimeException ex) {
            // handle exception (details omitted)
        }
    }
}
