package com.mastertheboss.amq;


import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class MessageConsumer {

    @Incoming("queueA-transformed")
    public void sinkA(Long msg) {
        System.out.println("Message from Transformer: "+msg);;
    }
    @Incoming("queueB-transformed")
    public void sinkB(String msg) {
        System.out.println("Message from Transformer: "+msg);

    }


}