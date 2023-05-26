import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            
            String message = "Hello, server!";
            writer.println(message);
            
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
