package com.mastertheboss.servlet;

import javax.inject.Inject;
import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;



@WebServlet(value = "/json1")
public class OMServlet extends HttpServlet {


    // Parse/Create JSON with Object Model API
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            PrintWriter writer = resp.getWriter();

            JsonObject obj = Json.createObjectBuilder()
                    .add("firstname", "John")
                    .add("surname", "Doe")
                    .add("age", 40)
                    .add("groups", Json.createArrayBuilder()
                            .add("Admin")
                            .add("Developer")).build();
            writer.write("<h3>Basic JSON</h3>");
            Json.createWriter(writer).writeObject(obj);

            JsonArray jsonArray = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("firstname", "John")
                            .add("surname", "Doe")
                            .add("age", 40)
                            .build())
                    .add(Json.createObjectBuilder()//Another object builder to build JSON
                            .add("firstname", "Anna")
                            .add("surname", "Smith")
                            .add("age", 32)
                            .build()).build();
            writer.write("<h3>JSON Array</h3>");
            Json.createWriter(writer).writeArray(jsonArray);

            writer.write("<h3>JSON Reader</h3>");
            JsonReader jsonReader = Json.createReader(this.getClass().getClassLoader().getResourceAsStream("data.json"));
            obj = jsonReader.readObject();

            Map<String, Object> properties = new HashMap<>(1);
            properties.put(JsonGenerator.PRETTY_PRINTING, true);
            StringWriter sw = new StringWriter();
            JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
            JsonWriter jsonWriter = writerFactory.createWriter(sw);

            jsonWriter.writeObject(obj);
            jsonWriter.close();
            System.out.println(sw.toString());
            writer.write(sw.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}