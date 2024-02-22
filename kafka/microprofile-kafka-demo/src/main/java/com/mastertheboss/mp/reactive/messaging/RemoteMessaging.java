
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
    @Outgoing("to-kafka")
    public WeatherData producWeatherData(WeatherData message) {
        System.out.println("Sent WeatherData to Remote System : " + message);
        return message;
    }

    @Incoming("from-kafka")
    public CompletionStage<Void> consumeWeatherData(Message<WeatherData> message) {       
        System.out.println("Consumed WeatherData from Remote System: " + message.getPayload());
        return message.ack();
            
    }




/*
 
    @Incoming("sender")
    @Outgoing("to-kafka")
    public Message<WeatherData> sendToKafka(WeatherData msg) {
 
        
        try {
          
         
           System.out.println(msg.getCity() + "  "+msg.getTemperature());
            Message<WeatherData> m = Message.of(msg);
            // Just use the hash as the Kafka key for this example
            int key = msg.getCity().hashCode();

            // Create Metadata containing the Kafka key
            OutgoingKafkaRecordMetadata<Integer> md = OutgoingKafkaRecordMetadata
                    .<Integer>builder()
                    .withKey(key)
                    .build();

            // The returned message will have the metadata added
            return KafkaMetadataUtil.writeOutgoingKafkaMetadata(m, md);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
    }

    @Incoming("from-kafka")
    public CompletionStage<Void> receiveFromKafka(Message<WeatherData> message) {
        try {
            WeatherData payload = message.getPayload();

            IncomingKafkaRecordMetadata<Integer, WeatherData> md = KafkaMetadataUtil.readIncomingKafkaMetadata(message).get();
            String msg =
                    "Got Message with MetaData from from Kafka\n" +
                    "\t%s\n" +
                    "\tkey: %d; partition: %d, topic: %s";
            msg = String.format(msg, payload, md.getKey(), md.getPartition(), md.getTopic());
            System.out.println(msg);
          
        
            return message.ack();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    */
}