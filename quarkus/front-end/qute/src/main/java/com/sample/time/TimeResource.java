package com.sample.time;

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

@Path("/time")
public class TimeResource {

    @Inject
    Template time;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        List<TimeFormat> data = new ArrayList<>();
        data.add(new TimeFormat("UTC", "Western European Time"));
        data.add(new TimeFormat("UT", "Universal Time"));
        data.add(new TimeFormat("GMT", "Greenwich Time"));
        return time.data("data", data);
    }


    @TemplateExtension
    static String wiki(TimeFormat  data) {
        return "https://en.wikipedia.org/w/index.php?search="+data.getName()+"&fulltext=Search";
    }

}
