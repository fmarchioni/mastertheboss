package com.mastertheboss.kstreams;


import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@ApplicationScoped
public class PaymentGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentGenerator.class);

    private final Random random = new Random();

    @Outgoing("new-payment")
    public Multi<Payment> generatePayments() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> {
                    Payment payment = new Payment(generateRandomMail(), random.nextInt(1000));
                    System.out.println("Produced payment: " + payment);
                    return payment;
                });
    }


    @Incoming("large-payment")
    public void processLargePayment(Payment payment) {
        logBlueText("Received large payment! " + payment);
    }

    @Incoming("small-payment")
    public void processSmallPayment(Payment payment) {
        logWhiteText("Received small payment! " + payment);
    }
    void logBlueText(String text) {
        LOG.info("\u001B[34m" + text + "\u001B[0m");
    }

    void logWhiteText(String text) {
        LOG.info("\u001B[37m" + text + "\u001B[0m");
    }

    public String generateRandomMail() {
        String[] domains = {"example.com", "test.com", "domain.com"};
        String[] usernames = {"user123", "john_doe", "jane_smith"};

        String randomUsername = usernames[ThreadLocalRandom.current().nextInt(usernames.length)];
        String randomDomain = domains[ThreadLocalRandom.current().nextInt(domains.length)];

        // Generate a random number between 0 and 1
        double randomValue = Math.random();

        if (randomValue < 0.5) {
            // Return a correct email address
            return randomUsername + "@" + randomDomain;
        } else {
            // Return an intentionally incorrect email address
            return "incorrect_email"; // Modify this as needed
        }
    }


}
