/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yasin
 */
import java.net.*;
import java.io.*;

public class MyServer {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public MyServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting client");
            socket = server.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";
            while (!line.equals("over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                } catch (IOException i) {
                    System.out.println("Error " + i.getMessage());
                }
            }

            System.out.println("Connection closed");
            socket.close();
            in.close();
        } catch (Exception e) {
            System.out.println("Error here " + e.getMessage());
        }

    }

    public static void main(String args[]) 
    { 
        MyServer server = new MyServer(5000);
        
    }
    
    
    
}
