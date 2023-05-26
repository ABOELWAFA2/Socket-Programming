//1. Write a java program to print the name and address of your host.


import java.io.*;
import java.net.*;

public class HostInfoPrinter {
    public static void main(String[] args) {
        try {
            // Get the local host address
            InetAddress localhost = InetAddress.getLocalHost();

            // Print the host name and address
            System.out.println("Host Name: " + localhost.getHostName());
            System.out.println("Host Address: " + localhost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
