package com.sample;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;

import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.QueryParam;

import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.lra.annotation.Compensate;
import org.eclipse.microprofile.lra.annotation.Complete;
import org.eclipse.microprofile.lra.annotation.ws.rs.LRA;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Singleton
@Path("/lra")
@LRA(LRA.Type.SUPPORTS)
public class TransactionParticipant {

    @Inject
    TransactionUtil txUtil;

    @LRA
    @GET
    @Path("/begin")
    public Response begin(@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) String lraId,
            @QueryParam("name") @DefaultValue("guest") String name) {
        System.out.println("Start Transaction with LRA ID = " + lraId);

        try {
            Files.write(Paths.get(txUtil.getId(lraId)), name.getBytes());
        } catch (IOException e) {

            e.printStackTrace();
        }

        return txUtil.checkExpression(name) ? Response.ok().build() : Response.status(500).build();
    }

    @Complete
    @PUT
    @Path("/complete")
    public Response complete(@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) String lraId) {
        System.out.println("Complete ID = " + lraId);
        new File(txUtil.getId(lraId)).renameTo(new File(txUtil.getId(lraId) + ".done"));

        return Response.ok().build();
    }

    @Compensate
    @PUT
    @Path("/compensate")
    public Response compensate(@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) String lraId) {
        new File(txUtil.getId(lraId)).renameTo(new File(txUtil.getId(lraId) + ".failed"));
        System.out.println("Compensate ID = " + lraId);
        return Response.ok().build();
    }
}