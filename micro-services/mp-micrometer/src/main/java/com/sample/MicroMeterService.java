package com.sample;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.DistributionSummary;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
 


import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

 

@Path("/demo")
public class MicroMeterService {

    @Inject
    private MeterRegistry registry;

    @GET
    @Path("counter")
    public String counter() {
      
        registry.counter("dummy").increment();
        return "Counter is " + registry.counter("dummy").count();
    }

  
    @GET
    @Path("timer")
    public double timer() {

        Timer timer = registry.timer("demo_timer", Tags.of("ts", "" + System.currentTimeMillis()));
        // recording execution time of code
        timer.record(() -> {
            try {
                Thread.sleep((long) (Math.random() * 1000L));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
             
        });
        return timer.totalTime(TimeUnit.MILLISECONDS); 
         
    }

    @GET
    @Path("summary")

    public void summary() {

        DistributionSummary summary = DistributionSummary
        .builder("my_summary")
        .description("The summary description") // optional
        .register(registry);
        // Record values to distributionSummary
        summary.record(10);
        summary.record(20);
        summary.record(30);
         
    }
    
    @GET
    @Path("gauge")

    public int gauge() {

        AtomicInteger myGauge = registry.gauge("numberGauge", new AtomicInteger(0));

        // ... elsewhere you can update the value it holds using the object reference
        myGauge.set(10);
        return myGauge.get();
         
    }


}