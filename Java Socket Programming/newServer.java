//Author: Emmanuel Adefuye
//Project: Java Chat (Socket Programming)
//Date: 10/27/2021
 
import java.io.*;
import java.net.*;
import java.util.*;

public class newServer{
    
    private ServerSocket serverSocket;

    public static void main(String args[]) throws IOException
    {
        ServerSocket ss = new ServerSocket(5000); //port number
        newServer server = new newServer(ss);
        server.runServer();
    }

    public newServer(ServerSocket serverSocket) //this is the constructor
    {
        this.serverSocket = serverSocket;
    }

    public void runServer() //initiates the the server
    {
        try{
            while(serverSocket.isClosed() != true){
                Soceket socket = serverSocket.accept();
                System.out.println("client connected");
                clientMessenger messenger = new clientMessenger(socket);

                Thread thread = new Thread(clientMessenger); //multi-threading
                thread.start();
            }
        } catch (IOException e){

        }
    }

    public void endServer()
    {
        try{
            if(serverSocket != null){ //if a socket to be closed exists
                serverSocket.close();
                e.printStackTrace();
            }
        }
    }
}