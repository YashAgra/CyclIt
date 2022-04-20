import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
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


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Reader.init(System.in);
        Feedback.addFeedBack(3);

//        while(true){
//            System.out.println("1. Add Stand \n" +
//                    " 2. for list stand by id \n " +
//                    " 3. to list all stand \n" +
//
//                    " 4. Add Cycle \n "+
//
//                    " -1. to exit:\n ");
//            int i =Reader.nextInt();
//            if(i==1) addStand();
//            if(i==2) listStandById();
//            if(i==3) listAllStand();
//            if(i==-1) break;
//
//            switch(i){
//                case 1: addStand();
//                case 2: listStandById();
//                case 3: listAllStand();
//                case 4: addCycle();
//                case -1: break;
//            }
//        }


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
        for(int i=0;i<standList.size();i++){
            System.out.println("Stand Info--- Id: "+ standList.get(i).getId()+ " Location: "+ standList.get(i).getLocation()+" No. of cycles Available: "+ standList.get(i).getCycleCount());
        }
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
