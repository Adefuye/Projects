import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Formatter;
import java.io.ObjectOutputStream;

public class  StudentDatabaseSerializedFileWriter
{
    private static ObjectOutputStream studentsInfo = null;
    private StudentDatabase database;
    public StudentDatabaseSerializedFileWriter(){}//emptry constructor

    public static void writeData(StudentDatabase database) {
        try{
            studentsInfo.writeObject(database);
        }
        catch(IOException e)
        {
            System.err.println("Error serializing file");
        }
        
    }
}