//. A network application consists of a client and a server. The client sends a message to 
//the server which receives and prints it on its console.


import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Create a server socket on port 12345
            System.out.println("Server is listening on port 12345");

            Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
            System.out.println("Client connected");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = reader.readLine(); // Read the message sent by the client

            System.out.println("Received message: " + message);

            reader.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

