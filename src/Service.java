import java.io.*;
import java.sql.*;
import java.util.*;

public class Service {
    static Database db = Cyclit.db;
    private int serviceID; //assigning a service ID
    private int employeeID; //Assign a Employee ID
    private String maintenanceInformation; //Feedback information can be placed here
    private int ticket;

    public Service(){

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

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public void addService() throws IOException, SQLException{
        System.out.println("Enter Maintenance Information regarding cycle :");
        String maintenanceInfo = Reader.nextLine();
        

    }
}
