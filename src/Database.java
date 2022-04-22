import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;
import java.util.function.LongToIntFunction;

public class Database {
    public static final String connection_url = "jdbc:mysql://localhost:3306/cycleit";
    public static final String user = "root";
    public static final String password = "123456789";
    Connection connection = null;
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
    //public static Employee getEmployee(String type){}
}
