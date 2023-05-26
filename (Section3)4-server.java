/* Develop a network application with the client-server architecture in which the server 
solves a quadratic equation of the form (ax2+bX+c =0). The clients send the 
coefficients a, b, and c to the server in one message and receive the result from the 
server in one message. The client should prints the result on the console. Also the 
client should get the coefficients from the user. The server must run in an infinite loop 
waiting for clients’ requests.
*/
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1234);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            System.out.println("Server running...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String equation = new String(receivePacket.getData()).trim();
                System.out.println("Received equation: " + equation);

                String[] coefficients = equation.split(",");
                double a = Double.parseDouble(coefficients[0]);
                double b = Double.parseDouble(coefficients[1]);
                double c = Double.parseDouble(coefficients[2]);

                String result = solveQuadraticEquation(a, b, c);
                sendData = result.getBytes();

                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String solveQuadraticEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return "Roots are real and different. Root 1 = " + root1 + ", Root 2 = " + root2;
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return "Roots are real and same. Root = " + root;
        } else {
            return "Roots are complex.";
        }
    }
}
