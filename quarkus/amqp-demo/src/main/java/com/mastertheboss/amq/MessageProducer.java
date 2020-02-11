package com.mastertheboss.amq;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import io.reactivex.Flowable;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class MessageProducer {

    @Outgoing("sourceA")
    public Flowable<Long> generate() {
        return
        Flowable.interval(1, TimeUnit.SECONDS)
                .take(20);

    }

    @Outgoing("sourceB")
    public PublisherBuilder<String> source() {
        return ReactiveStreams.of("hello", "from", "quarkus");
    }



}
