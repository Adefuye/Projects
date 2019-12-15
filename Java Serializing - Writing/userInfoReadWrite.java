import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class userInfoReadWrite
{
    public static void main(String[] args)
    {
        //create some user Objects
        user usr1 = new user("emaxthedestroyer", "pass123!");
        user usr2 = new user("barlow", "goatStatus99");
        user usr3 = new user("metalFrae", "letsGetIt13");
        user usr4 = new user("atom_23", "carwash2@2019");
    }
}//end main

public static void writeOutput()
{
    try{
        FileOutputStream file = new FileOutputStream(new File("userData.txt"));
        ObjectOutputStream output = new ObjectOutputStream(file);//Pass in the output file

        //write out each object (the user's data)
        output.writeObject(usr1);
        output.writeObject(usr2);
        output.writeObject(usr3);
        output.writeObject(usr4);

        //close the file streams
        output.close();
        file.close();
    }
    catch(IOException e)
    {
        System.err.println("Error initializing the Input/Output stream");
    }
    catch(FileNotFoundException e)
    {
        System.err.println("File was not found");
    }
}

public static void readInput()
{
    try{

        FileInputStream inputFile = new FileInputStream(new File("userData.txt"));
        ObjectInputStream input = new ObjectInputStream(inputFile);//pass in the input file

        //reading the objects back in
        user readUsr1 = (user) input.readObject();
        user readUsr2 = (user) input.readObject();
        user readUsr3 = (user) input.readObject();
        user readUsr4 = (user) input.readObject();

        //printing user info to the screen
        System.out.println(readUsr1.toString());
        System.out.println(readUsr2.toString());
        System.out.println(readUsr3.toString());
        System.out.println(readUsr4.toString());

        //close the input streams
        input.close();
        inputFile.close();
    }
    catch(IOException e)
    {
        System.err.println("Error initializing the Input/Output stream");
    }
    catch(FileNotFoundException e)
    {
        System.err.println("Readable file was not found");
    }

}