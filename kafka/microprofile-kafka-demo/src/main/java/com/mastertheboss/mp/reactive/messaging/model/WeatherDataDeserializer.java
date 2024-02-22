
package com.mastertheboss.mp.reactive.messaging.model;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Timestamp;


public class WeatherDataDeserializer implements Deserializer<WeatherData> {

    @Override
    public WeatherData deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data))){
            String city = in.readUTF();
            Integer temperature= in.readInt();
            return new WeatherData(city,temperature);
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
