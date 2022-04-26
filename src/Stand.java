import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Stand {
    static Database db = Cyclit.db;
    private int id;
    private String location;
    private int cycleCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Stand() {
    }

    public int getCycleCount() {
        return cycleCount;
    }

    public void setCycleCount(int cycleCount) {
        this.cycleCount = cycleCount;
    }

    public Stand( String location, int cycleCount) {
        this.location = location;
        this.cycleCount = cycleCount;
    }

    public static void addStand() throws IOException, SQLException {
        System.out.println("Enter Stand Location: ");
        String address = Reader.nextLine();
        System.out.println("Enter No of Cycles on the stand: ");
        int cycles = Reader.nextInt();
        Stand stand = new Stand(address,cycles);
        db.addStand(stand);
    }
    public static void getById() throws IOException, SQLException {
        System.out.println("Enter stand id: ");
        int id = Reader.nextInt();
        Stand returnStand= db.getStandById(id);
        System.out.println("Stand Info--- Id: "+ returnStand.getId()+ " Location: "+ returnStand.getLocation()+" No. of cycles Available: "+ returnStand.getCycleCount());
    }
    public static void listAll() throws IOException, SQLException{
        ArrayList<Stand> standList = db.getAllStand();
        for(int i=0;i<standList.size();i++){
            System.out.println("Stand Info--- Id: "+ standList.get(i).getId()+ " Location: "+ standList.get(i).getLocation()+" No. of cycles Available: "+ standList.get(i).getCycleCount());
        }
    }
    public  void updateStandCycles(int cycleCount) throws IOException, SQLException{
        Stand stand = db.getStandById(this.id);
        stand.cycleCount = this.cycleCount;
        db.updateStandCycles(stand);
    }

    public static void deleteStand() throws SQLException, IOException {
        System.out.println("Enter Stand ID to delete: ");
        int id = Reader.nextInt();
        db.deleteStand(id);
    }

}

