/*develop A network application in java using multithreading   Develop a network application with the client-server architecture in which the server 
solves a quadratic equation of the form (ax2+bX+c =0). The clients send the 
coefficients a, b, and c to the server in one message and receive the result from the 
server in one message. The client should prints the result on the console. Also the 
client should get the coefficients from the user. The server must run in an infinite loop 
waiting for clients’ requests.*/


import java.io.*;
import java.net.*;

public class QuadraticServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server started. Listening on port 8888...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler handler = new ClientHandler(clientSocket);
                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read the coefficients a, b, and c from the client
            String coefficients = reader.readLine();
            String[] coeffs = coefficients.split(",");
            double a = Double.parseDouble(coeffs[0]);
            double b = Double.parseDouble(coeffs[1]);
            double c = Double.parseDouble(coeffs[2]);

            String result = solveQuadraticEquation(a, b, c);

            writer.println(result);

            writer.close();
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String solveQuadraticEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return "Roots are real and different. Root 1 = " + root1 + ", Root 2 = " + root2;
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return "Roots are real and same. Root = " + root;
        } else {
            return "Roots are complex.";
        }
    }
}
