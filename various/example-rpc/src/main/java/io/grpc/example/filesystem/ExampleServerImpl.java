package io.grpc.example.filesystem;



import com.mastertheboss.filesystem.FileManagerGrpc;
import com.mastertheboss.filesystem.Directory;
import com.mastertheboss.filesystem.FileList;

import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;


public final class ExampleServerImpl extends FileManagerGrpc.FileManagerImplBase {
  private static final Logger logger = Logger.getLogger(ExampleServerImpl.class.getName());

  @Override
  public void readDir(Directory req, StreamObserver<FileList> responseObserver)  {
    String content = null;

      File f = new File(req.getName());
      if (!f.isDirectory())  {
          throw new RuntimeException(req.getName() + " is not a directory.");
      }
      String[] pathnames = f.list();
      StringBuffer sb = new StringBuffer();
        // For each pathname in the pathnames array
        for (String pathname : pathnames) {
            // Print the names of files and directories
            sb.append(pathname);
            sb.append("\n");
        }
      content = sb.toString();

    FileList reply = FileList.newBuilder()
        .setList(content)
        .build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

}
