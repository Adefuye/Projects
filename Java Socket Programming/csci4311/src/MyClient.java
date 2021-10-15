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

public class MyClient {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public MyClient(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            input = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

        String line = "";
        while (!line.equals("over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }
        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) 
	{ 
		MyClient client = new MyClient("localhost", 5000); 
	}
    
    
}
