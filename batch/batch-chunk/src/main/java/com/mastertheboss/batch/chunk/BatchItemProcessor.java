
package com.mastertheboss.batch.chunk;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.util.StringTokenizer;

 
@Named
public class BatchItemProcessor implements ItemProcessor {
   
    @Override
    public JsonObject processItem(Object t) {
        
        StringTokenizer tokens = new StringTokenizer((String)t, ",");

        String name = tokens.nextToken();
        String surname = tokens.nextToken();
        String age = tokens.nextToken();
        
        JsonObject model = Json.createObjectBuilder()
        		   .add("firstName", name)
        		   .add("lastName", surname)
        		   .add("age", age)
        		   .build();
        		   
        
        
        return model;
    }
}
