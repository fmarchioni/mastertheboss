
package com.mastertheboss.mp.reactive.messaging;

import com.mastertheboss.mp.reactive.messaging.model.WeatherData;
 
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.util.concurrent.CompletionStage;



@ApplicationScoped
public class RemoteMessaging {
   


  
    @Incoming("sender")
    @Outgoing("to-amq")
    public WeatherData producWeatherData(WeatherData message) {
        System.out.println("Sent WeatherData to Remote System : " + message);
        return message;
    }

    @Incoming("from-amq")
    public CompletionStage<Void> consumeWeatherData(Message<WeatherData> message) {       
        System.out.println("Consumed WeatherData from Remote System: " + message.getPayload());
        return message.ack();
            
    }


}