package com.sample;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Path("/time")
public class TimeResource {

    // Matches with time.html
    @Inject
    Template time;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@QueryParam("zoneId") String zoneId) {
        Date date = null;
        try {
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(zoneId));
            date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time.data("zone",date);
    }

}
