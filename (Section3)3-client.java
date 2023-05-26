import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    private static final int PORT = 12345; 

    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getByName("localhost"); 
            DatagramSocket clientSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a number: ");
            int number = scanner.nextInt();
            String request = String.valueOf(number);
            byte[] sendData = request.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, PORT);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Factorial of " + number + " is: " + response);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
