import java.io.*;
import java.net.*;
import java.util.Scanner;

public class QuadraticClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the coefficient a: ");
            double a = scanner.nextDouble();
            System.out.print("Enter the coefficient b: ");
            double b = scanner.nextDouble();
            System.out.print("Enter the coefficient c: ");
            double c = scanner.nextDouble();
            
            writer.println(a + "," + b + "," + c);
            
            String result = reader.readLine();
            System.out.println("Result: " + result);
            
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
