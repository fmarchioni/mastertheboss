package com.mastertheboss.amq;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;

import java.util.Arrays;

@ApplicationScoped
public class MessageProducer {

    @Outgoing("sourceA")
    public Multi<Long> generate() {
        return Multi.createFrom().iterable(Arrays.asList(1l, 2l, 3l, 4l, 5l));
    }

    @Outgoing("sourceB")
    public PublisherBuilder<String> source() {
        return ReactiveStreams.of("hello", "from", "quarkus");
    }



}
