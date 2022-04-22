import java.io.*;
import java.sql.*;
import java.util.*;

public class Service {
    static Database db = Cyclit.db;
    private int serviceID; //assigning a service ID
    private int employeeID; //Assign a Employee ID
    private int cycleID;
    private String maintenanceInformation; //Feedback information can be placed here
    private int ticket;

    /* Administration function has been created in Service!*/
    public Service(int cycleID, String maintenanceInformation, int employeeID){
        this.cycleID = cycleID;
        this.maintenanceInformation = maintenanceInformation;
        this.employeeID = employeeID;
        this.ticket = 1; //Every time service is added, a ticket is opened!
    }

    public static void setDb(Database db) {
        Service.db = db;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setMaintenanceInformation(String maintenanceInformation) {
        this.maintenanceInformation = maintenanceInformation;
    }

    public static void addService() throws IOException, SQLException{
        System.out.println("Enter Cycle ID to which maintenance is associated");
        int inputCycleID = Reader.nextInt();
        System.out.println("Enter Maintenance Information regarding cycle :");
        String infoForRepair = Reader.nextLine();
        System.out.println("Randomly alloting Employee for Job!");
        int employRandom = db.getEmployeeID();
        Service service  = new Service(inputCycleID,infoForRepair,employRandom);
        db.addService(service);
    }

    public static void closeTicket() throws IOException, SQLException {
        System.out.println("Enter service ID");
        int sid = Reader.nextInt();
        db.updateTicket(sid); //change instance to 0;
    }

    public static void updateMaintenanceInService() throws IOException, SQLException {
        System.out.println("Enter service ID");
        int sid = Reader.nextInt();
        System.out.println("Input the new maintenance information");
        int updatedInfo = Reader.nextInt();
        db.updateMaintenance(sid);
    }

}
