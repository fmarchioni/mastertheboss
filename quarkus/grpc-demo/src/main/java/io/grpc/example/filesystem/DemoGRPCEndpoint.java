package io.grpc.example.filesystem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.mastertheboss.filesystem.*;
 
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/filesystem")
public class DemoGRPCEndpoint {

	// A service interface using the Mutiny API	
    @GrpcClient("filesystem")
    FileManagerGrpc.FileManagerBlockingStub blockingService;

    @GrpcClient("filesystem")
    FileManager service;

    
    @GET
    @Path("/blocking")
    public String serviceBlocking(@QueryParam("dir") String dir) {
    	FileList reply = blockingService.readDir(Directory.newBuilder().setName(dir).build());
        return reply.getList();

    }

    @GET
    @Path("/mutiny")
    public Uni<String> serviceNonBlocking(@QueryParam("dir") String dir) {
        return service.readDir(Directory.newBuilder().setName(dir).build())
                .onItem().transform((reply) -> reply.getList());
    }
    

}
