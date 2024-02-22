
package com.mastertheboss.mp.reactive.messaging.model;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class WeatherDataSerializer implements Serializer<WeatherData> {
    @Override
    public byte[] serialize(String topic, WeatherData data) {
        if (data == null) {
            return null;
        }

        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeUTF(data.city());
            out.writeInt(data.temperature());
            out.close();
            return bout.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
