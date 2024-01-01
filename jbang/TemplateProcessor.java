//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS jakarta.json.bind:jakarta.json.bind-api:3.0.0
//DEPS org.eclipse:yasson:3.0.3

//DEPS jakarta.json:jakarta.json-api:2.1.2
//DEPS org.glassfish:jakarta.json:2.0.1

//SOURCES Person.java
//PREVIEW
//JAVA 21+

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.json.JsonReader;
import jakarta.json.Json;
import java.io.StringReader;
import jakarta.json.JsonObject;
import java.io.StringWriter;
import java.util.Collections;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.JsonWriter;

public class TemplateProcessor {

    static String customerId;

    public static void main(String[] args) throws Exception {

        // Create a StringTemplate processor that can parse JSON strings
        var JSON = StringTemplate.Processor.of(
            // Callback function that processes JSON strings
            (StringTemplate stJSON) -> {
                try (JsonReader jsonReader = Json.createReader(new StringReader(
                    stJSON.interpolate()))) {
                    // Read the JSON object from the StringReader
                    return jsonReader.readObject();
                }
            }
        );
        Person person = new Person(12345678, "John", "Smith", "john.smith@example.com", "+1234567890");
        String strPerson = STR."""
                {
                    "customerId": "\{person.customerId()}",
                    "firstName": "\{person.firstName()}",
                    "lastName": "\{person.lastName()}",
                    "email": "\{person.email()}",
                    "phone": "\{person.phone()}"
                }
                """;
        System.out.println(String.format("JSON String built with Template: %s", strPerson));      
                JsonObject jsonObject = JSON."""
                    {
                        "customerId": "\{person.customerId()}",
                        "firstName": "\{person.firstName()}",
                        "lastName": "\{person.lastName()}",
                        "email": "\{person.email()}",
                        "phone": "\{person.phone()}"
                    }
                    """;
        // Deserialize JSON to Person record
        try {
            StringWriter stringWriter = new StringWriter();

            // Create a JsonWriter with pretty printing (indentation) configuration
            JsonWriter jsonWriter = Json.createWriterFactory(
                    Collections.singletonMap(JsonGenerator.PRETTY_PRINTING, true))
                    .createWriter(stringWriter);
    
            // Write the JSON object with indentation to the StringWriter
            jsonWriter.writeObject(jsonObject);
    
            // Close the JsonWriter to release resources
            jsonWriter.close();
    
            // Get the formatted JSON string from the StringWriter
            String formattedJson = stringWriter.toString();
    
            // Print the formatted JSON
            System.out.println("Formatted JSON Object:");
            System.out.println(formattedJson);
        } catch (JsonbException e) {
            e.printStackTrace();
        }
    }
}
