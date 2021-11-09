//Author: Emmanuel Adefuye
//Project: Java Chat (Socket Programming)
//Date: 10/27/2021

/* Client Messenger will handle sending messages that were sent from one
client to the server, then send it to all the other clients.
For multithreading, it 'implements runnable' so that each client
is handled by a separate thread*/

import java.net.*;
import java.util.*;
import java.io.*;

public class clientMessenger implements Runnable
{
    //arrayList to hold all the clients and handle sending messages to all
    public static ArrayList<clientMessenger> messengers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader; //read messages from clients
    private BufferedWriter bufferedWriter; //send messages to all clients
    private String clientName;

    public clientMessenger(Socket socket){//this is the constructor
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            this.clientName = bufferedReader.readLine();
            messengers.add(this); //adds new client into the ArrayList
            sendMessage(clientName + " has joined the server");//sends the message to all clients
        } catch (IOException e){
            shutDown(socket, bufferedReader, bufferedWriter); //closses everything
        }
    }

    @Override
    public void run()
    {
        String clientMessage;

        while(socket.isConnected())//while connected to a client
        {
            try{
                clientMessage = bufferedReader.readLine();
                sendMessage(clientMessage);//sends the message to all clients
            }catch(IOException e){
                shutDown(socket, bufferedReader, bufferedWriter);//closses everything
                break;
            }
        }
    }

    public void sendMessage(String sentMessage)
    {
        for(clientMessenger messenger : messengers)//loop through ArrayList of messengers
        {
            try{
                if(!messenger.clientName.equals(clientName)){
                    if(sentMessage == "bye"){
                        shutDown(socket, bufferedReader, bufferedWriter);//closses everything
                    }else{
                        messenger.bufferedWriter.write(sentMessage);
                        messenger.bufferedWriter.newLine();
                        messenger.bufferedWriter.flush();  
                    }
                    
                }
            }catch(IOException e){
                shutDown(socket, bufferedReader, bufferedWriter);//closses everything
            }
        }
    }

    public void clientDisconnect()
    {
        messengers.remove(this);
        sendMessage(clientName + " has been disconnected");
    }

    public void shutDown( Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
        clientDisconnect();
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}