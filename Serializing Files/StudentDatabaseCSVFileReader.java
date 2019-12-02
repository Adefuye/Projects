import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class StudentDatabaseCSVFileReader {

    private static Scanner input;

    public static void openFile (String filename) {

        try {
            input = new Scanner(Paths.get(filename));
        }   
        catch (IOException e) {
            System.err.println("Error opening file.  Terminating");
            System.exit(1);
        }        
    }    

    public static StudentDatabase readData() {
        StudentDatabase db = new StudentDatabase();
        int numLines = 0;
        while (input.hasNext()) {
            String line = input.nextLine();
            numLines++;
            String[] fields = line.split(","); // CSV file so split on commas.  (name contains a comma, so beware)
 //           System.out.println(fields.length);
            if (numLines != 1)
                parseDataFromCSVFile(fields, db);
        }                    
        return db;
    }

    private static void parseDataFromCSVFile(String[] fields, StudentDatabase db) {
        Student ts = new Student();
        // parse and set student ID
        int id = Integer.parseInt(fields[0]);
        ts.setStudentID(id);
        // parse and set sex
        ts.setSex(fields[1]);
        // parse and set Ethnic Group
        ts.setEthnicGroup(fields[2]);
        // parse and set name - the name is first, last in fields at indices 3 and 4
        // also we need to get rid of double quotes
        StringBuilder lastName =  new StringBuilder(fields[3]);
        lastName.deleteCharAt(0);
        //System.out.println(lastName);
        StringBuilder firstAndMiddle = new StringBuilder(fields[4]);
        firstAndMiddle.deleteCharAt(fields[4].length()-1);
        firstAndMiddle.deleteCharAt(0);
        String firstAndMiddleString = firstAndMiddle.toString();
        String[] nameFields = firstAndMiddleString.split(" "); // the first and middle are parsed together so split them
        ts.setFirstName(nameFields[0]);
        ts.setMiddleInitial(nameFields[1]);
        ts.setLastName(lastName.toString());
        // set primary program
        ts.setProgram(fields[5]);
        // set academic plan
        ts.setAcademicPlan(fields[6]);
        // set subplan
        ts.setSubPlan(fields[7]);
        // set start level
        int sl = Integer.parseInt(fields[8]);
        ts.setStrtLevel(sl);
        // set "total" ?
        double tot = Double.parseDouble(fields[9]);
        ts.setTotal(tot);
        // set GPA
        double gpa = Double.parseDouble(fields[10]);
        ts.setGPA(gpa);
        // set FAFSA (on file?)
        ts.setFAFSA(fields[11]);
        double takePrgrs = Double.parseDouble(fields[12]);
        ts.setTakePrgrs(takePrgrs);
        //add student to the database
        double need = Double.parseDouble(fields[13]);
        ts.setFinancialNeed(need); 
        db.add(ts);
    }

    public static void closeFile() {
        if (input != null)
            input.close();
        input = null;
    }

}
