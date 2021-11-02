//Author: Emmanuel Adefuye
//Project: Java Chat (Socket Programming)
//Date: 10/27/2021

/* Client Messenger will handle sending messages that were sent from one
client to the server, then send it to all the other clients.
For multithreading, it 'implements runnable' so that each client
is handled by a separate thread*/
public class clientMessenger implements Runnable
{
    //arrayList to hold all the clients and handle sending messages to all
    public static ArrayList<clientMessenger> messengers = ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader; //read messages from clients
    private BufferedWritter bufferedWritter; //send messages to all clients
    private String clientName;

    public clientMessenger(Socket socket){//this is the constructor
        try{
            this.socket = socket;
            this.bufferedWritter = new BufferedWritter(
                new OutputStreamWriter(socket.getOutputStream())
            );

            this.bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            this.clientName = bufferedReader.readLine();
            clientMessenger.add(this); //adds new client into the ArrayList
            sendMessage(clientName + " has joined the server");
        } catch (IOException e){
            shutDown(socket, bufferedReader, bufferedWritter);
        }
    }

    @Override
    public void run()
    {

    }
}