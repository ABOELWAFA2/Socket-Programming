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
            System.out.print("Enter a number: ");
            int number = scanner.nextInt();
            writer.println(number);            
            long factorial = Long.parseLong(reader.readLine());
            System.out.println("Factorial: " + factorial);
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
