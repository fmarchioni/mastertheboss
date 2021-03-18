package com.mastertheboss.reactive;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.HashMap;
import java.util.Random;

@ApplicationScoped
public class QuoteConverter {
    HashMap<String,Double> quotes;


    private Random random = new Random();
    @PostConstruct
    public void init() {
        quotes = new HashMap<>();
        String company[] = new String[] {
                "Acme","Globex","Umbrella","Soylent","Initech" };

        for (String c: company)
        quotes.put(c, new Double(random.nextInt(100) + 50));

    }

    @Incoming("stocks")
    @Outgoing("in-memory-stream")
    @Broadcast
    public String newQuote(String quoteJson) {
        Jsonb jsonb = JsonbBuilder.create();

        Operation operation = jsonb.fromJson(quoteJson, Operation.class);

        double currentQuote = quotes.get(operation.getCompany());
        double newQuote;
        double change = (operation.getAmount() / 25);

        if (operation.getType() == Operation.BUY) {
              newQuote = currentQuote + change;
        }
        else  {
            newQuote = currentQuote - change;
        }
        if (newQuote < 0) newQuote = 0;

        quotes.replace(operation.getCompany(), newQuote);
        Quote quote = new Quote(operation.getCompany(), newQuote);
        return jsonb.toJson(quote);

    }

}
