package ${package};

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/hello")
public class HelloREST {

    @GET
    public Response hello(){

        return Response
                .ok( "Hello World")
                .build();
    }

}