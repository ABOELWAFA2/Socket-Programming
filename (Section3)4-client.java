import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("localhost");
            int serverPort = 1234;
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter coefficient a: ");
            double a = scanner.nextDouble();
            System.out.print("Enter coefficient b: ");
            double b = scanner.nextDouble();
            System.out.print("Enter coefficient c: ");
            double c = scanner.nextDouble();

            String equation = a + "," + b + "," + c;
            sendData = equation.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String result = new String(receivePacket.getData()).trim();

            System.out.println("Result: " + result);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
