 
package org.jboss.as.quickstarts.helloworld;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

import javax.ejb.Stateless;

@Stateless
public class JsonPrettyPrintService {

	public String  printJSON(String name) throws Exception {
		JsonReader jsonReader = Json.createReader(this.getClass().getClassLoader().getResourceAsStream(name)); 
		JsonObject obj = jsonReader.readObject(); 
		Map<String, Object> properties = new HashMap<>(1); 
		properties.put(JsonGenerator.PRETTY_PRINTING, true); 
		StringWriter sw = new StringWriter(); 
		JsonWriterFactory writerFactory = Json.createWriterFactory(properties); 
		JsonWriter jsonWriter = writerFactory.createWriter(sw); 
		jsonWriter.writeObject(obj); 
		jsonWriter.close(); 
		return (sw.toString());


	}


}
