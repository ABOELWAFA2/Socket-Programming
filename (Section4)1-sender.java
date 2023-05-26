/* A network multicast application consists of a sender which sends a message to a 
multicast group one time only. Each receiver in the multicast group should receive the 
message and print it on its console. Use the IP “239.5.4.1” for the multicast group 
with port number 5600.
*/
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) {
        try {
            String multicastGroup = "239.5.4.1";
            int port = 5600;

            InetAddress group = InetAddress.getByName(multicastGroup);
            DatagramSocket socket = new DatagramSocket();

            String message = "This is a multicast message.";
            byte[] buffer = message.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
            socket.send(packet);

            System.out.println("Message sent to multicast group.");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
