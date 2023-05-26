import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver {
    public static void main(String[] args) {
        try {
            InetAddress groupIP = InetAddress.getByName("230.0.0.1");
            int port = 4446;

            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(groupIP);

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
