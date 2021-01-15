package com.mastertheboss.servlet;

import javax.inject.Inject;
import javax.json.*;
import javax.json.stream.JsonCollectors;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet(value = "/stream")
public class FilterJSON extends HttpServlet {


    // Parse/Create JSON with Object Model API
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            PrintWriter writer = resp.getWriter();



            JsonReader jsonReader = Json.createReader(this.getClass().getClassLoader().getResourceAsStream("data2.json"));
            JsonObject jsonObject = jsonReader.readObject();

            List<String> list = jsonObject.getJsonArray("fruits").stream()
                    .filter(jsonValue -> ((JsonString) jsonValue).getString().startsWith("a"))
                    .map(jsonValue -> ((JsonString) jsonValue).getString())
                    .collect(Collectors.toList());

            JsonArray jsonArray = jsonObject.getJsonArray("fruits").stream()
                    .filter(jsonValue -> ((JsonString) jsonValue).getString().startsWith("a"))
                    .collect(JsonCollectors.toJsonArray());

            for (String fruit: list)
            writer.write(fruit);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}