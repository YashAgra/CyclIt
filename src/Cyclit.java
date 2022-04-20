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


    public static void addStand() throws IOException, SQLException {
        System.out.println("Enter Stand Location: ");
        String address = Reader.nextLine();
        System.out.println("Enter No of Cycles on the stand: ");
        int cycles = Reader.nextInt();
        Stand stand = new Stand(address,cycles);
        db.addStand(stand);
        //input number

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Reader.init(System.in);
        while(true) {
            System.out.println("1 for add stand, 2 for list stand by id, 3 to list all stand, -1 to exit: ");
            int i = Reader.nextInt();
            if (i == 1) Employee.addtodb();
            if (i == 2) Employee.updatedb(2);
            if (i == 3) Employee.deletefromdb(2);
            if (i == -1) break;
        }


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
