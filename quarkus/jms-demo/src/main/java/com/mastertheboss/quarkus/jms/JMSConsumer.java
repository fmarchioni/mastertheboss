package com.mastertheboss.quarkus.jms;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@ApplicationScoped
public class JMSConsumer implements Runnable {


    @Inject
    ConnectionFactory connectionFactory;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    void onStart(@Observes StartupEvent ev) {
        scheduler.scheduleWithFixedDelay(this, 0L, 5L, TimeUnit.SECONDS);
    }

    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            javax.jms.JMSConsumer consumer = context.createConsumer(context.createQueue("exampleQueue"));
            while (true) {
                Message message = consumer.receive();
                if (message == null) {
                    return;
                }
                JsonReader jsonReader = Json.createReader(new StringReader(message.getBody(String.class)));
                JsonObject object = jsonReader.readObject();
                String msg = object.getString("message");
                System.out.println(msg);
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }



}
