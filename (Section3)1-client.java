import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            String message = "Hello, server!";
            byte[] buffer = message.getBytes();

            InetAddress serverAddress = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 8888);

            socket.send(packet);

            System.out.println("Message sent to the server.");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
