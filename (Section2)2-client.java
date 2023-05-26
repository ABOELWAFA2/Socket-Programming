import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a message: ");
            String message = scanner.nextLine();
            
            writer.println(message);
            
            String uppercaseMessage = reader.readLine();
            System.out.println("Received uppercase message: " + uppercaseMessage);
            
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
