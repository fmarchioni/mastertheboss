package com.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.qute.TemplateInstance;

@Path("help")
public class HelperResource {

    @Inject
    Template helper;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        List<String> data = new ArrayList<>();
        data.add("UTC");
        data.add("GMT");
        data.add("UT");
        return helper.data("data", data);
    }

    static HashMap<String,String> map = new HashMap();
    public HelperResource()  {
        map.put("UTC", "Western European Time");
        map.put("UT", "Universal Time");
        map.put("GMT", "Greenwich Time");
    }
    @TemplateExtension
    static String description(String  time) {
        return map.get(time);
    }

}
