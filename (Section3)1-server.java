/*develop  A network application  in java using UDP Sockets consists of a client and a server. The client sends a message to 
the server which receives and prints it on its console.*/


import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(8888);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Server started. Waiting for a message from the client...");

            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());

            System.out.println("Received message from the client: " + message);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
