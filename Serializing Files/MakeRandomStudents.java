import java.util.Random;

public class MakeRandomStudents {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage:  MakeRandomStudents numstudents");
            System.exit(1);
        }

        int numNames = Integer.parseInt(args[0]);

        String[] firstNames = {"Joe","Mary","Louis","Becky","Leonard","Steve","Stacey","Greg","James","Joan","Haydar","Lucy","Chris","Kris","Stan","Alice","Bob","Josie","Jose","Kendra","T-Bob","Maria","Andrew","Jill","Lars","Nolan","Doug","Xavier","Francine","Ann","Sridhar","Bhupinder","Jason","Walter","Brian","Nancy","Michael","Thien"};

        String[] middleInitials = {"A.","B.","C.","D.","E.","F.","G.","H.","I.","J.","K.","L.","M.","N.","O.","P.","Q.","R.","S.","T.","U.","V.","W.","X.","Y.","Z."};

        String[] lastNames = {"Jackson","Boudreaux","Thibodeaux","Landry","Nemoy","Kirk","Levitt","Pham","Nguyen","Wynn","Schwing","Johnson","Lee","Abdelguerfi","Bowen","Smith","Jones","Baker","Naquin","Sennheiser","Keonnigsegg","Stuart","Tudor","Abromov","Jaeger","Jarndyce","Cooper","Dufresne","Singh","Grisham","Humbert","Einstein","Feynman","Marsalis","Fang","Chen","Charagundala"};

        String[] ethnicities = {"Asian","Hispanic/Latino","White","Black/African American","Two or more races","Native American","Not Specified"};
        String[] fafsaOrNot = {"Y","N"};

        String[] subPlans = {"BIOINFORM","INFASSUR","GAMEDEV",""};
        String[] genders     = {"M","F"};

        // output a "header" line that describes the data
        System.out.println("ID,Sex,Ethnic Grp,Name,Prim Prog,Acad Plan,Sub-Plan,Strt Level,Total,GPA,FAFSA,Take Prgrs,Fin Need");

        Random rng = new Random();
        for (int i=0; i<numNames; i++) {
            Student s = new Student();
            // make a name
            String name = "\"" + lastNames[rng.nextInt(lastNames.length)] + ", " + firstNames[rng.nextInt(firstNames.length)] + " " + middleInitials[rng.nextInt(middleInitials.length)] + "\"";
            double gpa = 1.0 + rng.nextDouble() * 3.0;
            String sex = genders[rng.nextInt(genders.length)];
            int studentID = rng.nextInt(999999) + 2000000;
            String ethnicGrp = ethnicities[rng.nextInt(ethnicities.length)];
            String fafsa = fafsaOrNot[rng.nextInt(fafsaOrNot.length)];
            String subPlan = subPlans[rng.nextInt(subPlans.length)];
            int strtLevel = rng.nextInt(40);
            double total = 4.0 * rng.nextInt(8);
            double takePrgrs = (double)(rng.nextInt(18)+1);
            double financialNeed = (double)(rng.nextInt(10000));
    
/*
            System.out.println(studentID + "," + sex + "," + ethnicGrp + "," + name + 
                ",USCI,CSCI," + subPlan + "," + strtLevel + "," + total + "," + gpa + "," +
                fafsa + "," + takePrgrs + "," + financialNeed);
*/

            System.out.printf("%d,%s,%s,%s,USCI,CSCI,%s,%d,%.3f,%.3f,%s,%.3f,%.2f%n",studentID,sex,ethnicGrp,name,subPlan,strtLevel,total,gpa,fafsa,takePrgrs,financialNeed);

        }
    }
}
