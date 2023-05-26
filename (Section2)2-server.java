/*develop A network application in java using multithreading TCP servers.  consists of a client and a server. The client sends a message to 
the server which receives and prints it on its console, then the server converts it to 
uppercase and sends it back to the client who receives the message and prints on its 
console*/
import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server started. Listening on port 8888...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Receive the message from the client
                String message = reader.readLine();
                System.out.println("Received message from client: " + message);

                String uppercaseMessage = message.toUpperCase();

                writer.println(uppercaseMessage);

                writer.close();
                reader.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
