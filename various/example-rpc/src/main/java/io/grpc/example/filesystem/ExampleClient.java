package io.grpc.example.filesystem;


import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mastertheboss.filesystem.FileManagerGrpc;
import com.mastertheboss.filesystem.Directory;
import com.mastertheboss.filesystem.FileList;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;

public class ExampleClient {
  private static final Logger logger = Logger.getLogger(ExampleClient.class.getName());

  private final FileManagerGrpc.FileManagerBlockingStub blockingStub;

  public ExampleClient(Channel channel) {

    blockingStub = FileManagerGrpc.newBlockingStub(channel);
  }


  public void getFileList(String dir) {

    Directory request = Directory.newBuilder().setName(dir).build();
    FileList response;
    try {
      response = blockingStub.readDir(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
    logger.info("Response: " + response.getList());
  }


  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
       System.out.println("Proper Usage is: java ExampleClient directory");
       System.exit(0);
    }
    // Access a service running on the local machine on port 50051
    String target = "localhost:50051";



    ManagedChannel channel = ManagedChannelBuilder.forTarget(target)

        .usePlaintext()
        .build();
    try {
      ExampleClient client = new ExampleClient(channel);
      client.getFileList(args[0]);
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}
