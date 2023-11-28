package com.mastertheboss.kstreams;

import io.quarkus.kafka.client.serialization.ObjectMapperSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;


@ApplicationScoped
public class PaymentStream {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentStream.class);


    @Produces
    public Topology buildTopology() {

        StreamsBuilder builder = new StreamsBuilder();

        ObjectMapperSerde<Payment> valueSerde = new ObjectMapperSerde<>(Payment.class);
        Serde<String> keySerde = Serdes.String();

        KStream<String, Payment> stream = builder.stream("payment", Consumed.with(keySerde, valueSerde));

        stream
                .peek((key, payment) -> System.out.println("Received payment: " + payment))
                .filter((key, payment) -> {
                    boolean isValid = isValidEmail(payment.getEmail());
                    if (!isValid) {
                        logRedText("Invalid email encountered. Discarding payment.");
                    }
                    return isValid;
                })
                .mapValues(payment -> payment.getEmail() + " has paid $" + payment.getMoney())
                .foreach((key, value) -> logGreenText("Valid payment: " + value));

        stream
                .filter((key, payment) -> payment.getMoney() > 500)
                .to("largepayment", Produced.with(keySerde, valueSerde));

        stream
                .filter((key, payment) -> payment.getMoney() <= 500)
                .to("smallpayment", Produced.with(keySerde, valueSerde));


        return builder.build();
    }

    private boolean isValidEmail(String email) {
        // Regular expression to check for a valid email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    void logRedText(String text) {
        LOG.info("\u001B[31m" + text + "\u001B[0m");
    }

    void logGreenText(String text) {
        LOG.info("\u001B[32m" + text + "\u001B[0m");
    }
}
