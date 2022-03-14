package com.mastertheboss.amq;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;


import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class MessageTransformer {

    @Incoming("queueA")
    @Outgoing("queueA-transformed")
    public PublisherBuilder<Long> filter(PublisherBuilder<Long> input) {
        return input.filter(item -> item.longValue() > 3);
    }

    @Incoming("queueB")
    @Outgoing("queueB-transformed")
    public String toUpperCase(String payload) {
        System.out.println("Message received: "+payload);
        return payload.toUpperCase();
    }






}
