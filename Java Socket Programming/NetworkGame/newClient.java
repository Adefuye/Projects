//Author: Emmanuel Adefuye
//Project: Java Chat (Socket Programming)
//Date: 10/27/2021

//Intuition Game (number guessing/matching)
//Instructions: 2 players will try to use their intuition to guess the exact number the
//other player is going to pick between 1 and 5
//Goal is to try to guess correctly and match each other's number, meaning both players
//have a perfectly synced intuition.

import java.io.*;
import java.net.*;
import java.util.*;

public class newClient{
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;
    public int userNum;

    public newClient(Socket socket, String userName, int userNum){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = userName;
            this.userNum = userNum;
        }catch(IOException e){
            shutDown(socket, bufferedReader, bufferedWriter);
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("What is your name: ");
        String userName = input.nextLine();
        System.out.println("Pick a number from 1 to 5: ");
        int userNum = 1;

        try{
            Socket socket = new Socket("localhost", 5000);
            newClient client = new newClient(socket, userName, userNum);
            //initiate the receiving/sending of messages to the server
            client.receiveMessages();//separate threads (run at the same time)
            client.guessNum();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void guessNum()
    {
        try{
            bufferedWriter.write(userName);//sends user's name to buffer
            bufferedWriter.newLine();
            bufferedWriter.flush();//then sends to the clientMessenger

            Scanner input = new Scanner(System.in);

            while(socket.isConnected()){
                //System.out.println("Pick a number from 1 to 5: ");
                userNum = input.nextInt();

                if(userNum > 5 || userNum < 1){
                    System.out.println("Number must be from 1 to 5");
                }
                else{
                    //bufferedWriter.write(userName + ": " + userNum); //'clientMessage' in clientMessenger
                    bufferedWriter.write(userName+": ");
                    bufferedWriter.flush();
                    bufferedWriter.write(userNum+"");
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

    
}