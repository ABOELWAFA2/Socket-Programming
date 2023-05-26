import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {
    public static void main(String[] args) {
        try {
            String multicastGroup = "239.5.4.1";
            int port = 5600;

            InetAddress group = InetAddress.getByName(multicastGroup);
            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(group);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Receiver started. Waiting for multicast messages...");

            while (true) {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received message: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
