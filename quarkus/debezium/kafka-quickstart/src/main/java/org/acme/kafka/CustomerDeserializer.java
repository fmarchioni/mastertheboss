package org.acme.kafka;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;
import io.quarkus.runtime.annotations.RegisterForReflection;


import javax.json.*;
import java.io.StringReader;

@RegisterForReflection
public class CustomerDeserializer extends JsonbDeserializer<Customer> {

    public CustomerDeserializer() {
        super(Customer.class);
    }


    @Override
    public Customer deserialize(String topic, byte[] data) {
        JsonReader reader = Json.createReader(new StringReader(new String(data)));
        JsonObject jsonObject = reader.readObject();

        JsonObject payload = jsonObject.getJsonObject("payload");
        String firstName = payload.getJsonObject("after").getString("first_name");
        String lastName = payload.getJsonObject("after").getString("last_name");
        String email = payload.getJsonObject("after").getString("email");
        return new Customer(firstName,lastName,email);

    }
/* Might be useful to print recursively the JSON string
    public void printJsonObject(JsonObject jsonObj) {

        for (Object key : jsonObj.keySet()) {
            //based on you key types
            String keyStr = (String) key;
            Object keyvalue = jsonObj.get(keyStr);
            if (keyStr.equals("payload")) {
                JsonObject value = (JsonObject)keyvalue;
                String firstName = value.getJsonObject("after").getString("first_name");
                String lastName = value.getJsonObject("after").getString("last_name");
                String email = value.getJsonObject("after").getString("email");
                //Print key and value
                System.out.print("--- key: " + keyStr + " value: " + keyvalue);
            }
            //for nested objects iteration if required
            if (keyvalue instanceof JsonObject) {
                printJsonObject((JsonObject) keyvalue);
            }

        }
    }
*/
}