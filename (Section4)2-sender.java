/* A network multicast application consists of a sender which sends periodically a 
counter value N every second to a multicast group consists of 3 receivers each of them 
receives the counter value and prints it on its console. Use loops in both sides and 
selects the appropriate IP and port number for the multicast group.*/

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress groupIP = InetAddress.getByName("230.0.0.1");
            int port = 4446;

            int counter = 0;
            while (true) {
                String message = "Counter: " + counter;
                byte[] sendData = message.getBytes();

                DatagramPacket packet = new DatagramPacket(sendData, sendData.length, groupIP, port);
                socket.send(packet);

                System.out.println("Sent: " + message);

                counter++;

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
