/*. Implement a UDP Server that Run in Infinite Loop handling Multiple Client Requests, 
one at a time. The server receives a number N from the client then it calculates its 
factorial (N!) and sends back the result to the client. Test your server with multiple 
clients’ connection requests. A client should get the number N from the user, don’t 
hard code the number inside the client*/

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private static final int PORT = 12345; 

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            System.out.println("UDP Server is running on port " + PORT);

            while (true) {
                byte[] receiveData = new byte[1024];
                byte[] sendData;
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received request from client " + clientAddress + ":" + clientPort);
                System.out.println("Request: " + clientRequest);

                int number = Integer.parseInt(clientRequest);
                int factorial = calculateFactorial(number);
                String result = String.valueOf(factorial);
                sendData = result.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Sent response to client " + clientAddress + ":" + clientPort);
                System.out.println("Response: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int calculateFactorial(int number) {
        if (number == 0)
            return 1;
        else
            return number * calculateFactorial(number - 1);
    }
}
