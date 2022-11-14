package io.grpc.example.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import com.mastertheboss.filesystem.*;

@GrpcService
public class DemoGRPCService implements FileManager {
 
    
    @Override
    public Uni<FileList> readDir(Directory req) {
        File f = new File(req.getName());
        if (!f.isDirectory())  {
            throw new RuntimeException(req.getName() + " is not a directory.");
        }
        String name = req.getName();
        
        String[] pathnames = f.list();
        StringBuffer sb = new StringBuffer();
          // For each pathname in the pathnames array
          for (String pathname : pathnames) {
              // Print the names of files and directories
              sb.append("[File=" +pathname+"]");
 
          }
 
        
        return Uni.createFrom().item(sb.toString())
                .map(res -> FileList.newBuilder().setList(res).build());
    }
	 
}
