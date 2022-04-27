import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static final String connection_url = "jdbc:mysql://localhost:3306/cyclit";
    public static final String user = "root";
    public static final String password = "123456789";
    public static Connection connection = null;
    Database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connection_url, user, password);
    }

   //executeUpdate: create update, delete
    public void addStand(Stand stand) throws SQLException {
        PreparedStatement query = connection.prepareStatement("INSERT INTO stand(location, cycleCount) values(?,?)");
        query.setString(1, stand.getLocation());
        query.setInt(2, stand.getCycleCount());
        query.executeUpdate();
        query.close();
    }
    //executeQuery: read
    public ResultSet getAllStand() throws SQLException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM STAND");
//        ArrayList<Stand> returnList = new ArrayList<>();
//        while(resultSet.next()){
//            Stand stand = new Stand();
//            stand.setId(resultSet.getInt("id"));
//            stand.setLocation(resultSet.getString("location"));
//            stand.setCycleCount(resultSet.getInt("cycleCount"));
//            returnList.add(stand);
//        }
        return resultSet;
    }

    public Stand getStandById(int id) throws SQLException{
        PreparedStatement query = connection.prepareStatement("SELECT * FROM stand WHERE id = ?");
        query.setInt(1,id);
        ResultSet resultSet = query.executeQuery();
        Stand stand = new Stand();
        while(resultSet.next()){
            stand.setId(resultSet.getInt("id"));
            stand.setLocation(resultSet.getString("location"));
            stand.setCycleCount(resultSet.getInt("cycleCount"));
        }
        return stand;
    }

    public Employee getEmployee(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement query = connection.prepareStatement("SELECT * FROM Employee WHERE eid = ?");
        query.setInt(1,id);
        ResultSet resultSet = query.executeQuery();
        Employee employ = new Employee();
        while(resultSet.next()){
            employ.setEmployeeId(resultSet.getInt("eid"));
            employ.setAddress(resultSet.getString("Address"));
            employ.setEmailAddress(resultSet.getString("email"));
            employ.setPhoneNumber(resultSet.getString("phone"));
            employ.setType(resultSet.getString("Type"));
            employ.setName(resultSet.getString("Name"));
            employ.setSalary(resultSet.getInt("Salary"));
        }
        return employ;
    }

    public void addEmployee(Employee employee) throws SQLException {
        PreparedStatement query = connection.prepareStatement("INSERT INTO Employee(Address, email, phone, Type, Name, Salary) values(?,?,?,?,?,?)");
        query.setString(1,employee.getAddress());
        query.setString(2, employee.getEmailAddress());
        query.setString(3, employee.getPhoneNumber());
        query.setString(4, employee.getType());
        query.setString(5, employee.getName());
        query.setInt(6,employee.getSalary());
        query.setString(7, employee.getPassword());
        query.executeUpdate();
        query.close();
    }

    public void deleteEmployee(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("DELETE FROM Employee where eid = ?");
        query.setInt(1,id);
        query.executeUpdate();
        query.close();
    }

    public void updateEmployee(Employee employee) throws SQLException {
        PreparedStatement query = connection.prepareStatement("UPDATE Employee set Name = ?,phone = ?,Address = ? where eid = ? ");
        //query.setString(1,col);
        query.setString(1,employee.getName());
        query.setString(2,employee.getPhoneNumber());
        query.setString(3,employee.getAddress());
        query.setInt(4,employee.getEmployeeId());
        query.executeUpdate();
        query.close();
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement query = connection.prepareStatement("INSERT INTO User(Address, email, phone, Name,roll_no,Password,Wallet,Year) values(?,?,?,?,?,?,?,?)");
        query.setString(1,user.getAddress());
        query.setString(2,user.getEmailID());
        query.setString(3,user.getPhoneNumber());
        query.setString(4, user.getName());
        query.setInt(5, user.getRollNumber());
        query.setString(6,user.getPassword());
        query.setInt(7,user.getWallet());
        query.setInt(8,user.getYear());
        query.executeUpdate();
        query.close();
    }

    public void deleteUser(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("DELETE FROM User where user_id = ?");
        query.setInt(1,id);
        query.executeUpdate();
        query.close();
    }

    public User getUser(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("SELECT * FROM User WHERE user_id = ?");
        query.setInt(1,id);
        ResultSet resultSet = query.executeQuery();
        User user = new User();
        while(resultSet.next()){
            user.setUserID(resultSet.getInt("user_id"));
            user.setPassword(resultSet.getString("password"));
            user.setPhoneNumber(resultSet.getString("phone"));
            user.setWallet(resultSet.getInt("wallet"));
            user.setRollNumber(resultSet.getInt("roll_no"));
            user.setEmailID(resultSet.getString("email"));
            user.setYear(resultSet.getInt("year"));
            user.setAddress(resultSet.getString("address"));
            user.setName(resultSet.getString("name"));
        }
        return user;
    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement query = connection.prepareStatement("UPDATE User set Name = ?,phone = ?,Address = ?,Password = ? where user_id = ? ");
        query.setString(1,user.getName());
        query.setString(2,user.getPhoneNumber());
        query.setString(3,user.getAddress());
        query.setString(4,user.getPassword());
        query.setInt(5,user.getUserID());
        query.executeUpdate();
        query.close();
    }
    public ArrayList<UserTripHistory> gettripHistory(int id) throws SQLException {
        ArrayList<UserTripHistory> list = new ArrayList<UserTripHistory>();
        PreparedStatement query = connection.prepareStatement("select * from trip_history where uid = ?");
        query.setInt(1,id);
        ResultSet result = query.executeQuery();
        while(result.next()){
            UserTripHistory trip =  new UserTripHistory();
            trip.setDate(result.getString("date"));
            trip.setCycleId(result.getInt("cid"));
            trip.setDistance(result.getInt("distance"));
            trip.setDestStandId(result.getInt("dest_stand"));
            trip.setEndTime(result.getTime("end").toString());
            trip.setPayID(result.getInt("pid"));
            trip.setSourceStandID(result.getInt("source_stand"));
            trip.setStartTime(result.getTime("start").toString());
            trip.setUserID(result.getInt("uid"));
            list.add(trip);
        }
        return list;
    }

    public void updateWalletMoney(User user) throws SQLException {
        PreparedStatement query = connection.prepareStatement("UPDATE User set Wallet = ? where user_id = ? ");
        query.setInt(1,user.getWallet());
        query.setInt(2,user.getUserID());
        query.executeUpdate();
        query.close();
    }

    public void addOngoingRides(OngoingRides ride) throws SQLException {
        PreparedStatement query = connection.prepareStatement("Insert Into OngoigRides (UserID,CycleID,StandID,OutTime) Values (?,?,?,?)");
        query.setInt(1,ride.getUSerID());
        query.setInt(2,ride.getCycleID());
        query.setInt(3,ride.getStandID());
        query.setString(4,ride.getOutTime());
        query.executeUpdate();
        query.close();

    }

    public void deleteOngoigRides(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("Delete from OngoigRides where UserID = ?");
        query.setInt(1,id);
        query.executeQuery();
        query.close();
    }

    public OngoingRides getOngoigRides(int id) throws SQLException {
        OngoingRides ride = new OngoingRides();
        PreparedStatement query = connection.prepareStatement("Select * from OngoigRides where UserId = ?");
        query.setInt(1,id);
        ResultSet resultSet = query.executeQuery();
        ride.setUSerID(resultSet.getInt("UserID"));
        ride.setCycleID(resultSet.getInt("CycleID"));
        ride.setStandID(resultSet.getInt("StandID"));
        ride.setOutTime(resultSet.getString("OutTime"));
        return ride;
    }
    public void addCycle(Cycle cycle) throws SQLException{
        PreparedStatement query = connection.prepareStatement("INSERT INTO cycle( cycle_qr ,inUse ,stand_id, inRepair, model_no) values(?,?,?,?,?) ");
        query.setString(1,cycle.getCycle_qr());
        query.setBoolean(2,cycle.getInUse());
        query.setInt(3,cycle.getStand_id());
        query.setBoolean(4,cycle.getInRepair());
        query.setString(5,cycle.getModel_no());

        query.executeUpdate();
        query.close();
    }

    public void updateCycleStandId(int cid ,int sid) throws SQLException {
        PreparedStatement query= connection.prepareStatement("UPDATE cycle SET stand_id=? WHERE cycle_id= ?");
        query.setInt(1,sid);
        query.setInt(2,cid);
        query.executeUpdate();
        query.close();
    }

    public void UpdateCycleInUse(int cid, boolean inUse) throws SQLException {
        PreparedStatement query = connection.prepareStatement("UPDATE cycle SET inUse=? WHERE cycle_id= ?");
        query.setBoolean(1,inUse);
        query.setInt(2,cid);
        query.executeUpdate();
        query.close();
    }

    public void UpdateCycleInRepair(int cid, boolean inRepair) throws SQLException {
        PreparedStatement query = connection.prepareStatement("UPDATE cycle SET inRepair=? WHERE cycle_id= ?");
        query.setBoolean(1,inRepair);
        query.setInt(2,cid);
        query.executeUpdate();
        query.close();
    }

    public void updateStandCycles(Stand stand) throws SQLException{
        PreparedStatement query = connection.prepareStatement("update stand set cycleCount = ? where id = ?");
        query.setInt(1, stand.getCycleCount());
        query.setInt(2, stand.getId());
        query.executeUpdate();
        query.close();
    }



    public Cycle getCycleinfo(int cid) throws SQLException, ClassNotFoundException {
        PreparedStatement query = connection.prepareStatement("SELECT * FROM cycle WHERE cycle_id = ?");
        query.setInt(1,cid);
        ResultSet resultSet = query.executeQuery();
        Cycle c=new Cycle();

        while(resultSet.next()){
            c.setCycle_id(resultSet.getInt("cycle_id"));
            c.setCycle_qr((resultSet.getString("cycle_qr")));
            c.setInUse(resultSet.getBoolean("inUse"));
            c.setStand_id(resultSet.getInt("stand_id"));
            c.setInRepair(resultSet.getBoolean("InRepair"));
            c.setModel_no(resultSet.getString("model_no"));
        }
        return c;
    }

    public  ResultSet getAllCycle() throws SQLException, ClassNotFoundException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM cycle");
        return resultSet;
    }

    //Get all cycles on a stand
    public static ArrayList<Cycle> getAllCycle(int sid) throws SQLException, ClassNotFoundException {
        PreparedStatement query = connection.prepareStatement("SELECT * FROM CYCLE WHERE stand_id = ?");
        query.setInt(1,sid);
        ResultSet resultSet = query.executeQuery();
        ArrayList<Cycle> returnList = new ArrayList<>();
        while(resultSet.next()){
            Cycle cycle = new Cycle();
            cycle.setCycle_id(resultSet.getInt("cycle_id"));
            cycle.setCycle_qr((resultSet.getString("cycle_qr")));
            cycle.setInUse(resultSet.getBoolean("inUse"));
            cycle.setStand_id(resultSet.getInt("stand_id"));
            cycle.setInRepair(resultSet.getBoolean("InRepair"));
            cycle.setModel_no(resultSet.getString("model_no"));
            returnList.add(cycle);
        }
        return returnList;
    }

    public void deleteCycle(int cid) throws SQLException {
        PreparedStatement query = connection.prepareStatement("DELETE from cycle where cycle_id=?");
        query.setInt(1,cid);
        query.executeUpdate();
        query.close();
    }

    public  void addFeedBack(Feedback feed) throws SQLException {
        PreparedStatement query= connection.prepareStatement("INSERT INTO feedback(user_id,feedback) values(?,?) ");
        query.setInt(1,feed.getUser_id());
        query.setString(2,feed.getFeedback());

        query.executeUpdate();
        query.close();
    }

    public  ArrayList<Feedback> getAllFeedback() throws SQLException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM feedback");
        ArrayList<Feedback> returnList = new ArrayList<>();
        while(resultSet.next()){
            Feedback feed = new Feedback();
            feed.setFeedback_id(resultSet.getInt("feedback_id"));
            feed.setUser_id(resultSet.getInt("user_id"));
            feed.setFeedback(resultSet.getString("feedback"));
            returnList.add(feed);
        }

        return returnList;

    }

    public  void addPayInterface(Payment_interface pay) throws SQLException {
        PreparedStatement query= connection.prepareStatement("INSERT INTO payment_interface(user_id,amount,isWalletRecharge,status) values(?,?,?,?) ");
        query.setInt(1,pay.getUser_id());
        query.setInt(2,pay.getAmount());
        query.setBoolean(3,pay.isWalletRecharge());
        query.setBoolean(4,pay.getStatus());

        query.executeUpdate();
        query.close();
    }

    public  void UpdatePayInter_wallet(int user_id, boolean isWallet) throws SQLException {
        PreparedStatement query= connection.prepareStatement("UPDATE payment_interface SET isWalletRecharge=? where user_id=?");
        query.setBoolean(1,isWallet);
        query.setInt(2,user_id);

        query.executeUpdate();
        query.close();
    }

    public  void UpdatePayInter_status(int user_id, boolean status) throws SQLException {
        PreparedStatement query= connection.prepareStatement("UPDATE payment_interface SET status=? where user_id=?");
        query.setBoolean(1,status);
        query.setInt(2,user_id);

        query.executeUpdate();
        query.close();
    }

    public  void deletePayInterface_byUserId(int user_id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("DELETE from payment_interface where user_id=?");
        query.setInt(1,user_id);
        query.executeUpdate();
        query.close();
    }

    public void deleteStand(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("delete from stand where id = ?");
        query.setInt(1, id);
        query.executeUpdate();
        query.close();
    }
    //==================================================================================================================
    public int getEmployeeID()  throws SQLException { //To randomly get an employee ID
        ResultSet resultSet; //initializing variable
        Statement query = connection.createStatement();
        resultSet = query.executeQuery("SELECT eid FROM employee ORDER BY RAND() LIMIT 1"); //take random employee from table
        //note that the query has to be updated.
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("eid");
        }
        return id;
    }

    public void addService(Service service) throws SQLException { //Query Complete
        PreparedStatement query = connection.prepareStatement("INSERT INTO service(int cycle_id, String maint_info, int eid, int ticketStatus,int fid) values(?,?,?,?,?)");
        query.setInt(1,service.getCycleID());
        query.setString(2,service.getMaintenanceInformation());
        query.setInt(3,service.getEmployeeID());
        query.setBoolean(4,service.getTicket());
        query.setInt(5,service.getFid());

        query.executeUpdate();
        query.close();
    }

    public void closeTicket(int id) throws SQLException {
        PreparedStatement query= connection.prepareStatement("UPDATE service SET ticket=? where id=?");
        query.setBoolean(1,false);
        query.setInt(2,id);

        query.executeUpdate();
        query.close();
    }

    public void updateMaintenance(String updatedInfo, int sid) throws SQLException {
        PreparedStatement query= connection.prepareStatement("UPDATE service SET maint_info=? where id=?");
        query.setString(1,updatedInfo);
        query.setInt(2,sid);

        query.executeUpdate();
        query.close();
    }



    public ResultSet getAll_Active_Services() throws SQLException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM service where ticketStatus = False");
//        ArrayList<Service> returnList = new ArrayList<>();
//        while(resultSet.next()){
//            Service service = new Service();
//
//            boolean status=resultSet.getBoolean("ticketStatus");
//            if(status==true) continue;
//
//            service.setServiceId(resultSet.getInt("id"));
//            service.setEmployeeID(resultSet.getInt("eid"));
//            service.setCycleID(resultSet.getInt("cycle_id"));
//            service.setFid(resultSet.getInt("fid"));
//            service.setMaintenanceInformation(resultSet.getString("maint_info"));
//            service.setTicket(resultSet.getBoolean("ticketStatus"));
//            returnList.add(service);
//        }

        return resultSet;
    }

    public void getEmployeeDetials_publicInfo() throws SQLException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM employee_details");
        System.out.println("=============================Employee Details========================================\n");
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);
//        System.out.println("");
//        while(resultSet.next()){
//            System.out.println(resultSet.getInt("eid")+" "+
//                               resultSet.getString("name")+" "+
//                                resultSet.getString("email")+ " "+
//                                resultSet.getString("phone")+" "+
//                                resultSet.getString("address"));
//        }
    }

    public void getUserDetails_publicInfo() throws SQLException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM user_details");
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);
//        System.out.println("=============================User Details========================================\n");
//        System.out.println();
//        while(resultSet.next()){
//            System.out.println(resultSet.getInt("user_id")+" "+
//                    resultSet.getString("name")+" "+
//                    resultSet.getString("email")+ " "+
//                    resultSet.getString("phone")+" "+
//                    resultSet.getString("address"));
//        }
    }


    //10 Queries start from Here !

    //<Start with HR>
    public void averageCycleUserTime() throws SQLException {
        System.out.println("Greetings Cycle Manager! This is the average time each cycle is being used");
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery(""); // TODO add SQL
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);
    }

    public void totalAssetOfCyclit() throws SQLException {
        /* list of assets of Cyclit */

        System.out.println("Greetings HR! This is the total assets (wallet + payments) of total Cyclit application!");
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery(""); // TODO add SQL
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);
        //asset value
    }

    public void employeeNaturalJoinCustomer() throws SQLException {
        /* List all the employees who uses our app as a customer */

        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery(""); // TODO add sql
        System.out.println("Greetings HR! This is the list of all employees who use our application as a customer : ");
        /*select user.name, user.phone , user.email, user.address from user
        inner join employee on employee.eid = user.user_id; */
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);

    }

    public void averageSalaryofEmployeeTypes() throws SQLException {
        System.out.println("Greetings HR! This is the average value Salary according to HR department, PR department, Service Department and Cycle Manager Department !");
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT type, AVG(salary) AS val_1 FROM employee GROUP BY type;"); // TODO add sql

        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);

    }
    //<HR queries end>

    //<PR Team queries begin>
    public void completeUserData () throws SQLException {
        //find all the users of the app (employee union user)
        Statement query = connection.createStatement();
        System.out.println("Greetings PR! This is the list of all the users : This list is for both employee and users, and does not have any duplicates");

        ResultSet resultSet = query.executeQuery("SELECT name, address, email, phone FROM user UNION select name, address,email, phone from employee;"); // TODO add sql
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);

    }
        //System.out.println("Greetings PR!");

    public void averageSpendGreaterThanAmount() throws SQLException, IOException {
        //List of users with Average spend greater than certain amount which is inputed
        System.out.println("Greetings PR! Please input the amount by which you want to check for spending");
        int amount = Reader.nextInt(); //TODO use amount for this query, and input the same!

        System.out.println("Greetings PR! This is the list of cycle users which have spend them more than " +  amount +  " You have the authority to reward them now!");
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery(""); // TODO add sql
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);

    }

    public void usersWithEveryCycle() throws SQLException {
        System.out.println("Greetings PR! This is the list of users who have used every single cycle. Send Promo-code!");
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery(""); // TODO add sql
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);
        //Please note the query!
//        select distinct user.id, user.name
//        from user
//        where not exists((select cycle_id
//                from cycle
//                except
//                select user_history.cycle_id
//                from user_history
//                where user_history.cycleid = user.cycleid
    }

    public void countServiceConversionFromFeebackByUser() throws SQLException {
        /* join feedback and services tables and group it by user id to find the count of feedbacks submitted by each user */
        /* Count number of feedbacks by each user, which was converted to a Service */

        System.out.println("Greetings PR! These is table informs about feedback to service for each user. This is for data analytics that can be used to improve feedback");
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery(" "); // TODO add sql
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);
//        select user_id, count(id) as number_of_users
//        from feedback
//        group by user_id;
    }
    // <PR team end>

    // <Cycle Manager Queries begin>

    public void feedbackToService() throws SQLException {
        System.out.println("Greetings Cycle Managers! Check the services that were derived from feedback !");
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * from feedback natural join service;"); // TODO check SQL query that I inputed
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);
    }

    public void listOfServicesClosedbyEmployee() throws SQLException, IOException {
        /* Gives list of service closed by an employee which has been inputed. */
        System.out.println("Greetings Cycle Managers! This is the list of employees who have closed a service after completion ");

        System.out.println("Please Enter the Employee id value :");
        int eid = Reader.nextInt();
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery(""); // TODO add query
        net.efabrika.util.DBTablePrinter.printResultSet(resultSet);
    }


    // TODO 1 query left that will be inputted in user trip history function. Number of functions left to write = 0

    //Changes made

}
