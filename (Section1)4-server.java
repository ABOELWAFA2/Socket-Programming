/*4. Implement a TCP Server that Run in Infinite Loop handling Multiple Client Requests, 
one at a time. The server receives a number N from the client then it calculate its 
factorial (N!) and sends back the result to the client. Test your server with multiple 
clients’ connection requests. A client should get the number N from the user, don’t 
hard code the number inside the client*/

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server Listening on port 8888...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                
                handleClientRequest(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void handleClientRequest(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            int number = Integer.parseInt(reader.readLine());        
            long factorial = calculateFactorial(number);
            writer.println(factorial);
            writer.close();
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static long calculateFactorial(int number) {
        BigInteger fact = new BigInteger();
        fact = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
