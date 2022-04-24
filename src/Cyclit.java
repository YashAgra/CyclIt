import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Cyclit {

    static Database db;

    static {
        try {
            db = new Database();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    //-------------------------Stands--------------------------------------------------------
    public static void addStand() throws IOException, SQLException {
        System.out.println("Enter Stand Location: ");
        String address = Reader.nextLine();
        System.out.println("Enter No of Cycles on the stand: ");
        int cycles = Reader.nextInt();
        Stand stand = new Stand(address,cycles);
        db.addStand(stand);
        //input number

    }


    private static void listStandById() throws IOException, SQLException {
        System.out.println("enter stand id: ");
        int id = Reader.nextInt();
        Stand returnStand= db.getStandById(id);
        System.out.println("Stand Info--- Id: "+ returnStand.getId()+ " Location: "+ returnStand.getLocation()+" No. of cycles Available: "+ returnStand.getCycleCount());

    }

    private static void listAllStand() throws SQLException {
        ArrayList<Stand> standList = db.getAllStand();
        for (int i = 0; i < standList.size(); i++) {
            System.out.println("Stand Info--- Id: " + standList.get(i).getId() + " Location: " + standList.get(i).getLocation() + " No. of cycles Available: " + standList.get(i).getCycleCount());
        }
    }
    public static void login() throws IOException, SQLException {
        //enter user and pass
        System.out.println("Enter User ID: ");
        String userid = Reader.nextLine();
        System.out.println("Enter Password: ");
        String pass = Reader.nextLine();
        User user =User.getfromdb(userid, pass);
        if(user!=null){
            System.out.println("Welcome "+ user.getName()+"\n");
            System.out.println("================================================================");
            System.out.println("| id |            Stand Location            | Available Cycles |");
            System.out.println("================================================================");
            ArrayList<Stand> standList = db.getAllStand();
            for(int i = 0 ; i < standList.size() ; i++) {
                Stand stand = standList.get(i);
                System.out.print("  ");
                System.out.print(stand.getId());
                System.out.print("    |");
                System.out.print(stand.getLocation());
                int z = stand.getLocation().length();
                System.out.print(" ".repeat(41-z)+"|");
                System.out.println(stand.getCycleCount());

                //TODO HANDLE THE EXCEPTION IF USER ID IS NOT PRESENT
            }
            System.out.println("===================================================");
            System.out.println("1. Book a bike \n2. Check menu options");
            int id = Reader.nextInt();
            switch(id){
                case 1: bookCycle(user);
                case 2: displayMenu(user);
                case -1: break;
            }
        }
        //check the cred in database and login
    }

    private static void displayMenu(User user) {
        System.out.println("display menu");
    }

    private static void bookCycle(User user) throws IOException {
        int uid = user.getUserID();
        System.out.println("Enter the stand ID: ");
        int standId = Reader.nextInt();

    }

    public static void register() throws SQLException, IOException {
        System.out.println("Please enter the following details: ");
        User.addtodb();
        System.out.println("Hurray!! You are done here. Now you can login using these credentials.\n");
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Reader.init(System.in);
        while(true){
            System.out.println("Welcome to Cyclit \n 1. Login\n 2. Register\n 3. Quit\n");
            int i = Reader.nextInt();
            switch(i){
                case 1: login(); System.out.println("Welcome to Cyclit \n 1. Login\n 2. Register\n 3. Quit\n");
                case 2: register(); System.out.println("Welcome to Cyclit \n 1. Login\n 2. Register\n 3. Quit\n");
                case 3: break;
                //TODO CHECK THE BUG : REGISTER OPTION BECOMES ACTIVE AUTOMATICALLY
            }
        }
        /*
        login page ask the user to lgin
        2 option : login , register

        login() functio
        register(){ addUser }
         */






    }

    //-------------------------Cycle--------------------------------------------------------

    private static void addCycle() throws IOException, SQLException, ClassNotFoundException {
        Cycle.addCycle();
    }

    private static void UpdateCycleStandByCid(){
       // Cycle.UpdateCycleStandId();
    }
}
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
        return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }

}
