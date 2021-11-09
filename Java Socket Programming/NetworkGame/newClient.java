//Author: Emmanuel Adefuye
//Project: Java Chat (Socket Programming)
//Date: 10/27/2021
 
import java.io.*;
import java.net.*;
import java.util.*;

public class newClient{
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;

    public newClient(Socket socket, String userName){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = userName;
        }catch(IOException e){
            shutDown(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage()
    {
        try{
            bufferedWriter.write(userName);//sends user's name 
            bufferedWriter.newLine();
            bufferedWriter.flush();//then sends to the server/other clients

            Scanner input = new Scanner(System.in);//constant stream of input for user's messages
            while(socket.isConnected()){
                String userMessage = input.nextLine();
                if(userMessage == "bye" || userMessage == "exit" || userMessage == "quit"){
                    shutDown(socket, bufferedReader, bufferedWriter);
                }
                else{
                    bufferedWriter.write(userName + ": " + userMessage);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                
            }
        }catch(IOException e){
            shutDown(socket, bufferedReader, bufferedWriter);
        }
    }

    public void receiveMessages()//receives other clients' messages from the server
    {
        new Thread(
            new Runnable(){
            @Override
            public void run(){
                String recievedMsg;

                while(socket.isConnected()){
                    try{
                        recievedMsg = bufferedReader.readLine();
                        System.out.println(recievedMsg); //reads the received messages
                    }catch(IOException e){
                        shutDown(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void shutDown(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
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

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("What is your name: ");
        String userName = input.nextLine();

        try{
            Socket socket = new Socket("localhost", 5000);
            newClient client = new newClient(socket, userName);
            client.receiveMessages();//separate threads (run at the same time)
            client.sendMessage();
        }catch(IOException e){
            e.printStackTrace();
        }
        

        //initiate the receiving/sending of messages to the server
        
    }
}