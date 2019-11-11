package com.mastertheboss.jaxrs.service


import com.mastertheboss.jaxrs.model.SimpleProperty

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/hello")
class HelloService {
    val hello: String
        @GET
        @Path("/text")
        get() = "hello world!"
    val propertyJSON: SimpleProperty
        @GET
        @Path("/json")
        @Produces(MediaType.APPLICATION_JSON)
        get() {
            val p = SimpleProperty("key", "value")
            return p
        }
    val propertyXML: SimpleProperty
        @GET
        @Path("/xml")
        @Produces(MediaType.APPLICATION_XML)
        get() {
            val p = SimpleProperty("key", "value")
            return p
        }
}
