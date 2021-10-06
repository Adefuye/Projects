//Author: Emmanuel Adefuye
//Project: Java Chat (Socket Programming)
//Date: 10/04/2021
 
import java.io.*;
import java.net.*;
import java.util.*;

public class client{
    public static void main(String[] args) throws IOException
    {
        Socket s = new Socket("localhost", 4999);

        Scanner input = new Scanner(System.in);
        System.out.println("What is your name: ");
        String username = input.nextLine();
        System.out.println("message: ");
        String message = input.nextLine();


        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println(username);
        pr.flush();
    }
}