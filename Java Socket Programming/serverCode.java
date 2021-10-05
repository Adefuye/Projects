//Author: Emmanuel Adefuye
//Project: Java Chat (Socket Programming)
//Date: 10/04/2021

package net.codejava.networking.chat.server;
 
import java.io.*;
import java.net.*;
import java.util.*;

public class serverCode{
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(4999);
        Socket s = ss.accept();

        System.out.println("Client Connected");
    }
}