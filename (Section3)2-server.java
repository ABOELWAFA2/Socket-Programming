/*develop  A network application  in java using UDP Sockets  A network application consists of a client and a server. The client sends a message to 
the server which receives and prints it on its console, then the server converts it to 
uppercase and sends it back to the client who receives the message and prints on its 
console.*/


import java.net.*;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("Server started and listening on port " + PORT);

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String message = new String(request.getData(), 0, request.getLength());
                System.out.println("Received message from client: " + message);

                String responseMessage = message.toUpperCase();

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();
                byte[] responseData = responseMessage.getBytes();
                DatagramPacket response = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                socket.send(response);

                System.out.println("Sent uppercase message back to client: " + responseMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
