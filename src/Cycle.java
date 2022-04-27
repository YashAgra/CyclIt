
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class Cycle {

    //TODO:   also have to make cycle_qr as a primary key / unique key  . as 2 cycles with same qr_code are getting inserted.


    private int cycle_id;
    private String cycle_qr;
    private boolean inUse;
    private int stand_id;
    private boolean inRepair;
    private String model_no;

    public int getCycle_id() {
        return cycle_id;
    }

    public void setCycle_id(int cycle_id) {
        this.cycle_id = cycle_id;
    }

    public String getCycle_qr() {
        return cycle_qr;
    }

    public void setCycle_qr(String cycle_qr) {
        this.cycle_qr = cycle_qr;
    }

    public boolean getInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getStand_id() {
        return stand_id;
    }

    public void setStand_id(int stand_id) {
        this.stand_id = stand_id;
    }

    public boolean getInRepair() {
        return inRepair;
    }

    public void setInRepair(boolean inRepair) {
        this.inRepair = inRepair;
    }

    public String getModel_no() {
        return model_no;
    }

    public void setModel_no(String model_no) {
        this.model_no = model_no;
    }

    public Cycle() throws SQLException, ClassNotFoundException {}

    public Cycle(int cycle_id, String cycle_qr, boolean inUse , int stand_id , boolean inRepair, String model_no ) throws SQLException, ClassNotFoundException {
        this.cycle_id=cycle_id;     this.cycle_qr=cycle_qr;     this.inUse=inUse;
        this.stand_id=stand_id;     this.inRepair=inRepair;     this.model_no=model_no;
    }

    public static void addCycle() throws SQLException, ClassNotFoundException, IOException {
        Cycle cycle=new Cycle();

        System.out.print("Enter cycle_QR_Code (string) :");
        cycle.cycle_qr=Reader.nextLine();

        cycle.inUse=false;

        System.out.print("Cycle is in which stand (int) :");
        cycle.stand_id=Reader.nextInt();

        cycle.inRepair=false;

        System.out.print("Enter Model No (string) :");
        cycle.model_no=Reader.nextLine();

        Cyclit.db.addCycle(cycle);

    }

    public static void UpdateCycleStandId(int cid) throws SQLException, ClassNotFoundException, IOException{

        System.out.println("Enter new stand_id:");
        int sid=Reader.nextInt();

        Cyclit.db.updateCycleStandId(cid,sid);

    }

    public static void UpdateCycleInUse(int cid) throws SQLException, ClassNotFoundException, IOException{
        System.out.println("Enter 1 if cycle in use /else 0:");
        int use=Reader.nextInt();
        boolean flag= false;
        if(use>=1) flag=true;

        Cyclit.db.UpdateCycleInUse(cid,flag);


    }

    public static void UpdateCycleInRepair(int cid) throws SQLException, ClassNotFoundException, IOException{
        System.out.println("Enter 1 if cycle in repair /else 0:");
        int repair=Reader.nextInt();
        boolean flag= false;
        if(repair>=1) flag=true;

        Cyclit.db.UpdateCycleInRepair(cid,flag);
    }

    public static Cycle getCyleFromDb(int cid) throws SQLException, ClassNotFoundException {
        return Cyclit.db.getCycleinfo(cid);
    }

    public static void deleteCyclefromdb() throws SQLException, IOException {
        System.out.println("Enter cycle_id to be Deleted:");
        int cid=Reader.nextInt();
        Cyclit.db.deleteCycle(cid);
    }

    public static void listAllCycle() throws SQLException, ClassNotFoundException{
        ResultSet cycles = Cyclit.db.getAllCycle();
        net.efabrika.util.DBTablePrinter.printResultSet(cycles);
    }
    public static void listAllCycle(int sid) throws SQLException, ClassNotFoundException {

        ResultSet cycles=Cyclit.db.getAllCycle(sid);
        net.efabrika.util.DBTablePrinter.printResultSet(cycles);
//        System.out.println("=====================================Cycles===============================\n\n");
//
//        System.out.println("Cycle id    QR_code    Stand_ID    InUSe    InRepair     Model_no\n");
//        for(int i=0;i<cycles.size();i++){
//            Cycle cycle=cycles.get(i);
//            System.out.println(cycle.getCycle_id()+"  "+cycle.getCycle_qr()+"   "+cycle.getStand_id()+"  "+cycle.getInUse()+"  "+cycle.getInRepair()+"  "+cycle.getModel_no());
//        }
    }







}


