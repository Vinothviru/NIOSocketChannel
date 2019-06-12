import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Client {
   public static void main(String[] args) throws IOException {
      SocketChannel server = SocketChannel.open();
      SocketAddress socketAddr = new InetSocketAddress("10.51.5.123", 9000);
      server.connect(socketAddr);
      Path path = Paths.get("/home/test/eclipse/ZIDE/NIOClient/temp1.txt");
      FileChannel fileChannel = FileChannel.open(path, 
         EnumSet.of(StandardOpenOption.CREATE, 
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE)
         );      
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      while(server.read(buffer) > 0) {
         buffer.flip();
         fileChannel.write(buffer);
         buffer.clear();
      }
      fileChannel.close();
      System.out.println("File Received");
      server.close();
   
   }
}