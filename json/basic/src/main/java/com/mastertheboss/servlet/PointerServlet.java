package com.mastertheboss.servlet;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;



@WebServlet(value = "/pointer")
public class PointerServlet extends HttpServlet {


    // Parse/Create JSON with Object Model API
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            PrintWriter writer = resp.getWriter();
            JsonReader jr = Json.createReader(this.getClass().getClassLoader().getResourceAsStream("data.json"));
            JsonStructure js = jr.read();
            JsonPointer jp = Json.createPointer("/Actors/0");

            JsonObject jsonObject = (JsonObject) jp.getValue(js);
            writer.println(jsonObject.toString());

            JsonPointer jpChildren = Json.createPointer("/Actors/0/children");


            boolean hasChildren = jpChildren.containsValue(js);

            if (hasChildren)  {
            JsonArray all = (JsonArray) jpChildren.getValue(js);
            writer.println(all.size());
             for (int ii=0;ii<all.size();ii++)
                writer.println(all.get(ii));
            }

            JsonPointer jpBirthDate = Json.createPointer("/Actors/0/BirthDate");
            JsonString jsBirthDate = Json.createValue("July 3, 1962");
            js = jpBirthDate.add(js, jsBirthDate);

            JsonPointer jpChild = Json.createPointer("/Actors/1/children/-");
            JsonString jsChild = Json.createValue("New Baby");
            js = jpChild.add(js, jsChild);

            JsonPointer jsonPointer = Json.createPointer("");
            JsonObject jsonList = (JsonObject) jsonPointer.getValue(js);

            Map<String, Object> properties = new HashMap<>(1);
            properties.put(JsonGenerator.PRETTY_PRINTING, true);
            StringWriter sw = new StringWriter();
            JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
            JsonWriter jsonWriter = writerFactory.createWriter(sw);

            jsonWriter.writeObject(jsonList);
            jsonWriter.close();

            writer.println(sw.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}