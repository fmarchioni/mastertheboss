package org.acme.redis;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import io.smallrye.mutiny.Uni;

@Path("/redisclient")
public class RedisResource {
    @Inject
    RedisService service;

    @GET
    public Uni<List<String>> keys() {
        return service.keys();
    }

    @Path("/{command}/{parameter}")
    @POST
    public String execute(String command, String parameter) {
       return service.execute(command, parameter);
    }

    @POST
    public Data create(Data data) {
        service.set(data);
        return data;
    }

    @GET
    @Path("/{key}")
    public Data get(String key) {
        return new Data(key, service.get(key));
    }

    @PUT
    @Path("/{key}")
    public void increment(String key, Integer value) {
        service.increment(key, value);
    }

    @DELETE
    @Path("/{key}")
    public Uni<Void> delete(String key) {
        return service.del(key);
    }
}
