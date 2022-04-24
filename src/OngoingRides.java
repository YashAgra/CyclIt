import java.sql.SQLException;

public class OngoingRides {
    private int USerID;
    private int CycleID;
    private int standID;
    private String outTime;

    public OngoingRides() {

    }

    public int getUSerID() {
        return USerID;
    }

    public void setUSerID(int USerID) {
        this.USerID = USerID;
    }

    public int getCycleID() {
        return CycleID;
    }

    public void setCycleID(int cycleID) {
        CycleID = cycleID;
    }

    public int getStandID() {
        return standID;
    }

    public void setStandID(int standID) {
        this.standID = standID;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public OngoingRides(int USerID, int cycleID, int standID, String outTime) {
        this.USerID = USerID;
        CycleID = cycleID;
        this.standID = standID;
        this.outTime = outTime;
    }

    static void Addtodb(OngoingRides ride) throws SQLException {
        Cyclit.db.addOngoingRides(ride);
    }

    static OngoingRides getfromdb(int id) throws SQLException {
        return Cyclit.db.getOngoigRides(id);
    }
    static void deletefromdb(int id) throws SQLException {
        Cyclit.db.deleteOngoigRides(id);
    }
}
