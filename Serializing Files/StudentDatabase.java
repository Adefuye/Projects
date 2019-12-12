import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.io.Serializable;

public class StudentDatabase implements Serializable{

    private ArrayList<Student> theData;

    public StudentDatabase() {

        theData = new ArrayList<Student>();
    }

    public void add(Student s) {
        theData.add(s);
    }

    public int getNumStudents() {
        return theData.size();
    }

    public int getIndexOfStudentWithID(int studentID) {
        for (int i=0; i< theData.size(); i++) {
            if (theData.get(i).getStudentID() == studentID)
                return i;
        }
        return -1;
    } 

    public Student getStudent(int index) {
        return theData.get(index);
    }

    public void sortByGPA() {
        Collections.sort(theData, 
                        new Comparator<Student>() {
                            public int compare(Student s1, Student s2) {
                                if (s1.getGPA() > s2.getGPA())
                                    return -1;
                                else if (s1.getGPA() < s2.getGPA())
                                    return 1;
                                return 0;
                            }
                        } );
    }

    public ArrayList<Student> getData() {
        return theData;
    }

    public String toString() {
        StringBuilder returnVal = new StringBuilder();
        for (Student s : theData) {
            returnVal.append(s.toString());
            returnVal.append("\n");
        }
        return returnVal.toString();
    }

}
