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

  /** Construct client for accessing HelloWorld server using the existing channel. */
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
    logger.info("Greeting: " + response.getList());
  }


  public static void main(String[] args) throws Exception {
    String dir =  "/home/francesco/";
    // Access a service running on the local machine on port 50051
    String target = "localhost:50051";


    // Create a communication channel to the server, known as a Channel. Channels are thread-safe
    // and reusable. It is common to create channels at the beginning of your application and reuse
    // them until the application shuts down.
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target)

        .usePlaintext()
        .build();
    try {
      ExampleClient client = new ExampleClient(channel);
      client.getFileList(dir);
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}
