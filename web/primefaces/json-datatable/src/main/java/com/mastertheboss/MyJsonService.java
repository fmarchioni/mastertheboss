package com.mastertheboss;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

@ManagedBean(name = "jsonService")
@ApplicationScoped
public class MyJsonService {

    public List getData() throws Exception {
        String data = "  [{\n" + "    \"nm\": \"Harold II\",\n" + "    \"cty\": \"United Kingdom\",\n"
                + "    \"hse\": \"House of Wessex\",\n" + "    \"yrs\": \"1066\"\n" + "  },\n" + "  {\n"
                + "    \"nm\": \"William I\",\n" + "    \"cty\": \"United Kingdom\",\n"
                + "    \"hse\": \"House of Normandy\",\n" + "    \"yrs\": \"1066-1087\"\n" + "  },\n" + "  {\n"
                + "    \"nm\": \"William II\",\n" + "    \"cty\": \"United Kingdom\",\n"
                + "    \"hse\": \"House of Normandy\",\n" + "    \"yrs\": \"1087-1100\"\n" + "  },\n" + "  {\n"
                + "    \"nm\": \"Henry I\",\n" + "    \"cty\": \"United Kingdom\",\n"
                + "    \"hse\": \"House of Normandy\",\n" + "    \"yrs\": \"1100-1135\"\n" + "  },\n" + "  {\n"
                + "    \"nm\": \"Stephen\",\n" + "    \"cty\": \"United Kingdom\",\n"
                + "    \"hse\": \"House of Blois\",\n" + "    \"yrs\": \"1135-1154\"\n" + "  }]";

        List monarchList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i); //
            monarchList.add(new Monarch(json.getString("nm"), json.getString("cty"), json.getString("hse"),
                    json.getString("yrs")));
        }

        return monarchList;
    }

}
