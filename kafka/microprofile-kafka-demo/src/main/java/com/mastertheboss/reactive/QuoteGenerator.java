package com.mastertheboss.reactive;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.*;

@ApplicationScoped
public class QuoteGenerator {


     @Inject
     private MockExternalAsyncResource externalAsyncResource;

    @Outgoing("stock-quote")
    public CompletionStage<String> generate() {

        return externalAsyncResource.getNextValue();
    }


}
