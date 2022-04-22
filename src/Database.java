import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static final String connection_url = "jdbc:mysql://localhost:3306/cycleit";
    public static final String user = "root";
    public static final String password = "12345678";
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
    public ArrayList<Stand> getAllStand() throws SQLException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM STAND");
        ArrayList<Stand> returnList = new ArrayList<>();
        while(resultSet.next()){
            Stand stand = new Stand();
            stand.setId(resultSet.getInt("id"));
            stand.setLocation(resultSet.getString("location"));
            stand.setCycleCount(resultSet.getInt("cycleCount"));
            returnList.add(stand);
        }
        return returnList;
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
        PreparedStatement query = connection.prepareStatement("SELECT * FROM Employee WHERE EmployeeID = ?");
        query.setInt(1,id);
        ResultSet resultSet = query.executeQuery();
        Employee employ = new Employee();
        while(resultSet.next()){
            employ.setEmployeeId(resultSet.getInt("EmployeeID"));
            employ.setAddress(resultSet.getString("Address"));
            employ.setEmailAddress(resultSet.getString("EmailAddress"));
            employ.setPhoneNumber(resultSet.getString("PhoneNumber"));
            employ.setType(resultSet.getString("Type"));
            employ.setName(resultSet.getString("Name"));
            employ.setSalary(resultSet.getInt("Salary"));
        }
        return employ;
    }

    public void addEmployee(Employee employee) throws SQLException {
        PreparedStatement query = connection.prepareStatement("INSERT INTO Employee(Address, EmailAddress, PhoneNumber, Type, Name, Salary) values(?,?,?,?,?,?)");
        query.setString(1,employee.getAddress());
        query.setString(2, employee.getEmailAddress());
        query.setString(3, employee.getPhoneNumber());
        query.setString(4, employee.getType());
        query.setString(5, employee.getName());
        query.setInt(6,employee.getSalary());
        query.executeUpdate();
        query.close();
    }

    public void deleteEmployee(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("DELETE FROM Employee where EmployeeID = ?");
        query.setInt(1,id);
        query.executeUpdate();
        query.close();
    }

    public void updateEmployee(Employee employee) throws SQLException {
        PreparedStatement query = connection.prepareStatement("UPDATE Employee set Name = ?,PhoneNumber = ?,Address = ? where EmployeeID = ? ");
        //query.setString(1,col);
        query.setString(1,employee.getName());
        query.setString(2,employee.getPhoneNumber());
        query.setString(3,employee.getAddress());
        query.setInt(4,employee.getEmployeeId());
        query.executeUpdate();
        query.close();
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement query = connection.prepareStatement("INSERT INTO User(Address, EmailAddress, PhoneNumber, Name,RollNumber,Password,Wallet,Year) values(?,?,?,?,?,?,?,?)");
        query.setString(1,user.getAddress());
        query.setString(2,user.getEmailID());
        query.setString(3,user.getPhoneNumber());
        query.setString(4, user.getName());
        query.setInt(5, user.getRollNumber());
        query.setString(6,user.getPassword());
        query.setInt(7,user.getWallet());
        query.setInt(8,user.getYear());
        query.executeQuery();
        query.close();
    }

    public void deleteUser(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("DELETE FROM User where UserID = ?");
        query.setInt(1,id);
        query.executeUpdate();
        query.close();
    }

    public User getUser(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("SELECT * FROM User WHERE UserID = ?");
        query.setInt(1,id);
        ResultSet resultSet = query.executeQuery();
        User user = new User();
        while(resultSet.next()){
            user.setUserID(resultSet.getInt("UserID"));
            user.setPassword(resultSet.getString("Password"));
            user.setPhoneNumber(resultSet.getString("PhoneNumber"));
        }
        return user;
    }

    public void updateUser(User user) throws SQLException {
        PreparedStatement query = connection.prepareStatement("UPDATE User set Name = ?,PhoneNumber = ?,Address = ?,Password = ? where UserID = ? ");
        query.setString(1,user.getName());
        query.setString(2,user.getPhoneNumber());
        query.setString(3,user.getAddress());
        query.setString(4,user.getPassword());
        query.setInt(5,user.getUserID());
        query.executeUpdate();
        query.close();
    }

    public void addOngoingRides(OngoingRides ride) throws SQLException {
        PreparedStatement query = connection.prepareStatement("Insert Into OngoigRides (UserID,CycleID,StandID,OutTime) Values (?,?,?,?)");
        query.setInt(1,ride.getUSerID());
        query.setInt(2,ride.getCycleID());
        query.setInt(3,ride.getStandID());
        query.setString(4,ride.getOutTime());
        query.executeQuery();
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

    public static ArrayList<Cycle> getAllCycle() throws SQLException, ClassNotFoundException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM cycle");
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

    public static void addFeedBack(Feedback feed) throws SQLException {
        PreparedStatement query= connection.prepareStatement("INSERT INTO feedback(user_id,feedback) values(?,?) ");
        query.setInt(1,feed.getUser_id());
        query.setString(2,feed.getFeedback());

        query.executeUpdate();
        query.close();
    }

    public static ArrayList<Feedback> getAllFeedback() throws SQLException {
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

    public static void addPayInterface(Payment_interface pay) throws SQLException {
        PreparedStatement query= connection.prepareStatement("INSERT INTO payment_interface(user_id,amount,isWalletRecharge,status) values(?,?,?,?) ");
        query.setInt(1,pay.getUser_id());
        query.setInt(2,pay.getAmount());
        query.setBoolean(3,pay.isWalletRecharge());
        query.setBoolean(4,pay.getStatus());

        query.executeUpdate();
        query.close();
    }

    public static void UpdatePayInter_wallet(int user_id, boolean isWallet) throws SQLException {
        PreparedStatement query= connection.prepareStatement("UPDATE payment_interface SET isWalletRecharge=? where user_id=?");
        query.setBoolean(1,isWallet);
        query.setInt(2,user_id);

        query.executeUpdate();
        query.close();
    }

    public static void UpdatePayInter_status(int user_id, boolean status) throws SQLException {
        PreparedStatement query= connection.prepareStatement("UPDATE payment_interface SET status=? where user_id=?");
        query.setBoolean(1,status);
        query.setInt(2,user_id);

        query.executeUpdate();
        query.close();
    }

    public static void deletePayInterface_byUserId(int user_id) throws SQLException {
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


}
