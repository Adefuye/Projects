//Author: Emmanuel Adefuye
//Project: Java Chat (Socket Programming)
//Date: 10/04/2021
 
import java.io.*;
import java.net.*;
import java.util.*;

public class client{
    public static void main(String[] args) throws IOException
    {
        Socket s = new Socket("localhost", 5000);

        String message = "";
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        PrintWriter nameOut = new PrintWriter(s.getOutputStream());
        Scanner input = new Scanner(System.in);

        System.out.println("username = ");
        String username = input.nextLine();
        nameOut.println("Welcome "+username+"!");
        nameOut.flush();

        if (username != "")
        {
            while(message != "bye")
            {
                System.out.println("message: ");
                message = input.nextLine();

                pr.println(username + ": " + message);
                pr.flush();

                
            }
            if(message == "bye")
            {
                nameOut.println("Goodbye "+username+"!");
                pr.close();
            }
        }
        else if(username == ""){
            pr.close();
        }
    }
}