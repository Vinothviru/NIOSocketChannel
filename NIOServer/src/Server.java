import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {
   public static void main(String[] args) throws IOException {
      ServerSocketChannel serverSocket = null;
      SocketChannel client = null;
      serverSocket = ServerSocketChannel.open();
      serverSocket.socket().bind(new InetSocketAddress(9000));
      client = serverSocket.accept();
      //System.out.println("Connection Set:  " + client.getRemoteAddress());
      Path path = Paths.get("/home/test/eclipse/ZIDE/NIOFileTransfer/temp.txt");
      FileChannel fileChannel = FileChannel.open(path);
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      while(fileChannel.read(buffer) > 0) {
         buffer.flip();
         client.write(buffer);
         buffer.clear();
      }
      fileChannel.close();
      System.out.println("File Sent");
      client.close();
   }
}