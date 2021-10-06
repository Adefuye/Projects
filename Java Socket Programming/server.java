//Author: Emmanuel Adefuye
//Project: Java Chat (Socket Programming)
//Date: 10/04/2021
 
import java.io.*;
import java.net.*;
import java.util.*;

public class server{
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(4999);
        Socket s = ss.accept();


        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println(str + " Connected");
        System.out.println(str + ": [message goes here]" );
    }
}