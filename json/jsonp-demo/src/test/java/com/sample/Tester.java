package com.sample;

 

import java.io.StringReader;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.junit.Test;

import com.sample.helper.ClientGet;

public class Tester {

	@Test
	public void test() {
        String result = ClientGet.get("http://localhost:8080/jsonp-demo/json");
        System.out.println("result is "+result);
        final JsonParser parser = Json.createParser(new StringReader(result));
        String key = null;
        String value = null;
        while (parser.hasNext()) {
            final Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    key = parser.getString();
                    System.out.println(key);
                    break;
                case VALUE_STRING:
                    value = parser.getString();
                    System.out.println(value);
                    break;
            }
        }
        parser.close();
	}

}
