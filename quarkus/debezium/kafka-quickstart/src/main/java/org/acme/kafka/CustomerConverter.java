package org.acme.kafka;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;

@ApplicationScoped
public class CustomerConverter {

    private static final double CONVERSION_RATE = 0.88;

    @Incoming("debezium-connector")
    @Outgoing("my-data-stream")
    // Send to all subscribers
    @Broadcast
    // Acknowledge the messages before calling this method.
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public Customer process(Customer data) {

        System.out.println("Got changes from Customer table "+data);
        return data;
    }

}
