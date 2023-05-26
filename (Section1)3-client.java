import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to the server at localhost:12345
            System.out.println("Connected to server");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String message = "Hello, Server!"; // Message to send to the server
            writer.println(message); // Send the message to the server
            System.out.println("Sent message to server: " + message);

            String receivedMessage = reader.readLine(); // Receive the uppercase message from the server
            System.out.println("Received message from server: " + receivedMessage);

            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
