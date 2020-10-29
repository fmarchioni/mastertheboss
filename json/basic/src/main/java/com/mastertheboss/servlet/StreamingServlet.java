package com.mastertheboss.servlet;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@SuppressWarnings("serial")
@WebServlet(value = "/json2")
public class StreamingServlet extends HttpServlet {


    // Parse/Create JSON with Streaming API
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            PrintWriter writer = resp.getWriter();

            JsonGenerator generator = Json.createGenerator(writer);
            writer.write("<h3>Streaming JSON</h3>");

            generator.writeStartArray().writeStartObject().write("name", "John")
                    .write("surname", "Doe").writeEnd().writeStartObject()
                    .write("name", "Anna").write("surname", "Smith").writeEnd()
                    .writeEnd();
            generator.flush();

            writer.write("<h3>Parsing JSON</h3>");
            JsonParser parser = Json.createParser(this.getClass()
                    .getClassLoader().getResourceAsStream("data.json"));

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();

                switch (event) {
                    case START_ARRAY:
                        writer.write(event.toString() + "\n");
                        break;
                    case END_ARRAY:
                        writer.write(event.toString() + "\n");
                        break;
                    case START_OBJECT:
                        writer.write("Found JSON object \n");
                        break;
                    case END_OBJECT:
                        writer.write("\nEnd JSON object"+ "\n");
                        break;
                    case VALUE_FALSE:
                    case VALUE_NULL:
                    case VALUE_TRUE:
                    case KEY_NAME:
                        writer.write(event.toString() + " " + parser.getString()
                                + " - ");
                        break;
                    case VALUE_STRING:
                        writer.write(event.toString() + " " + parser.getString()
                                + " - ");
                        break;
                    case VALUE_NUMBER:
                        writer.write(event.toString() + " " + parser.getString());
                        break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}