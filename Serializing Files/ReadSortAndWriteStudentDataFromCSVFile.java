import java.util.ArrayList;
import java.io.Serializable;

public class ReadSortAndWriteStudentDataFromCSVFile {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Usage: ReadSortAndWriteStudentDataFromCSVFile inputfilename outputfilename");
            System.exit(1);
        }

        String infilename = args[0];
        String outfilename = args[1];

        StudentDatabaseCSVFileReader.openFile(infilename);
        StudentDatabase db = StudentDatabaseCSVFileReader.readData();
        StudentDatabaseCSVFileReader.closeFile();

        db.sortByGPA();

        StudentDatabaseCSVFileWriter.openFile(outfilename);
        StudentDatabaseCSVFileWriter.writeData(db);
        StudentDatabaseCSVFileWriter.closeFile();
         
    }
}
