import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to the server at localhost:12345

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.println("Hello, Server!"); // Send the message to the server
            writer.flush();

            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
