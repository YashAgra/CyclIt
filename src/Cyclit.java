import java.sql.SQLException;

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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Stand stand = new Stand(1, "New Girls Hostel", 7);
        db.addStand(stand);
        Stand returnStand = db.getStandById(1);
        System.out.println("Stand Info--- Id: "+ returnStand.getId()+ " Location: "+ returnStand.getLocation()+" No. of cycles Available: "+ returnStand.getCycleCount());

    }
}
