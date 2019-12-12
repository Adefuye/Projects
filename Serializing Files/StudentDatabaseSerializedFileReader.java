import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.EOFException;
import java.lang.NullPointerException;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * @author Emmanuel Adefuye
 */

public class StudentDatabaseSerializedFileReader
{
    private static ObjectInputStream studentsInfo = null;
    public StudentDatabase data;

    public StudentDatabaseSerializedFileReader(){}//emptry constructor

    //Open the in[ut stream
    public static void openInputStream(String filename)
    {
        try{
            studentsInfo = new ObjectInputStream(new FileInputStream(filename));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Error finding The file for writing");
        }
        catch(IOException e)
        {
            System.err.println("Error Openning the file InputOutput");
        }
    }

    public static void readStudents()
    {
        try{
           studentsInfo.readObject();
        }
        catch(IOException e)
        {
            System.err.println("Error finding the file (Does not exist)");
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void closeInputStream()
    {
        try{
            studentsInfo.close();
        }
        catch(EOFException e)
        {
            System.err.println("End of file error");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}