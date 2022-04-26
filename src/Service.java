import java.io.*;
import java.sql.*;
import java.util.*;

public class Service {
    static Database db = Cyclit.db; //Check if calling this is right

    private int service_id;
    private int cycleID;
    private int employeeID; //Assign a Employee ID
    private int fid; //feedback ID
    private String maintenanceInformation; //Feedback information can be placed here
    private boolean ticket;

    public int getService_id(){
        return service_id;
    }
    public void setServiceId(int service_id){
        this.service_id=service_id;
    }
    public int getFid() {
        return fid;
    }

    public static void setDb(Database db) {
        Service.db = db;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setCycleID(int cycleID) {
        this.cycleID = cycleID;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setMaintenanceInformation(String maintenanceInformation) {
        this.maintenanceInformation = maintenanceInformation;
    }

    public void setTicket(boolean ticket) {
        this.ticket = ticket;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getCycleID() {
        return cycleID;
    }

    public String getMaintenanceInformation() {
        return maintenanceInformation;
    }

    public boolean getTicket() {
        return ticket;
    }

    public Service(){}
    /* Administration function has been created in Service!*/
    public Service(int cycleID, String maintenanceInformation, int employeeID, int fid){
        this.cycleID = cycleID;
        this.maintenanceInformation = maintenanceInformation;
        this.employeeID = employeeID;
        this.ticket = true; //Every time service is added, a ticket is opened!
        this.fid = fid;
    }

    public static void addService() throws IOException, SQLException{
        System.out.println("Enter Cycle ID to which maintenance is associated");
        int inputCycleID = Reader.nextInt();
        System.out.println("Enter Maintenance Information regarding cycle :");
        String infoForRepair = Reader.nextLine();
        System.out.println("Randomly alloting Employee for Job!");
        int employRandom = db.getEmployeeID();
        System.out.println("Input Feedback ID of feedback to which service may be associated");
        int feedback_id = Reader.nextInt();
        Service service  = new Service(inputCycleID,infoForRepair,employRandom,feedback_id);
        db.addService(service);
    }

    public static void closeTicket() throws IOException, SQLException {
        System.out.println("Enter service ID you want to close:");
        int sid = Reader.nextInt();
        db.closeTicket(sid); //change instance to 0;
    }

    public static void updateMaintenanceInService() throws IOException, SQLException {
        System.out.println("Enter service ID");
        int sid = Reader.nextInt();
        System.out.println("Input the new maintenance information");
        String updatedInfo = Reader.nextLine();
        db.updateMaintenance(updatedInfo, sid);
    }

    public static ResultSet getAll_Active_Services() throws SQLException {
       ResultSet services = db.getAll_Active_Services();
        return services;
    }


}
