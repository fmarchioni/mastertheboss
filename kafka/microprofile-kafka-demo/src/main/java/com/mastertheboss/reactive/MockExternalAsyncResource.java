package com.mastertheboss.reactive;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;


@ApplicationScoped
public class MockExternalAsyncResource {
    private static final int TICK = 2000;
    private Random random = new Random();
    String company[] = new String[] {
            "Acme","Globex","Umbrella","Soylent","Initech" };

    private ScheduledExecutorService delayedExecutor = Executors.newSingleThreadScheduledExecutor(Executors.defaultThreadFactory());
    private final AtomicInteger count = new AtomicInteger(0);
    private long last = System.currentTimeMillis();

    @PreDestroy
    public void stop() {
        delayedExecutor.shutdown();
    }

    public CompletionStage<String> getNextValue() {
        synchronized (this) {
            CompletableFuture<String> cf = new CompletableFuture<>();
            long now = System.currentTimeMillis();
            long next = TICK + last;
            long delay = next - now;
            last = next;
            NextQuote nor = new NextQuote(cf);
            delayedExecutor.schedule(nor, delay , TimeUnit.MILLISECONDS);
            return cf;
        }
    }

    private class NextQuote implements Runnable {
        private final CompletableFuture<String> cf;

        public NextQuote(CompletableFuture<String> cf) {
            this.cf = cf;
        }

        @Override
        public void run() {
            String _company = company[random.nextInt(5)];
            int amount = random.nextInt(100);
            int op = random.nextInt(2);
            Jsonb jsonb = JsonbBuilder.create();
            Operation operation = new Operation(op, _company, amount);
            cf.complete(jsonb.toJson(operation));

        }
    }
}