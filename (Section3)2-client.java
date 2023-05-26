import java.net.*;

public class Client {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();

            String message = "Hello, server!";
            byte[] requestData = message.getBytes();
            DatagramPacket request = new DatagramPacket(requestData, requestData.length, serverAddress, PORT);
            socket.send(request);
            System.out.println("Sent message to server: " + message);

            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            String responseMessage = new String(response.getData(), 0, response.getLength());
            System.out.println("Received uppercase message from server: " + responseMessage);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
