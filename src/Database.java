import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static final String connection_url = "jdbc:mysql://localhost:3306/cycleit";
    public static final String user = "root";
    public static final String password = "12345678";
    Connection connection = null;
    Database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connection_url, user, password);
    }

   //executeUpdate: create update, delete



    /*===============================STAND TABLE FUNCTIONS==========================================================*/
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
//    public void updateStandLocation(Stand stand) throws SQLException {
//        PreparedStatement query = connection.prepareStatement("update stand set location = ? where id = ?");
//        query.setString(1, stand.getLocation());
//        query.setInt(2, stand.getId());
//        query.executeUpdate();
//        query.close();
//    }
    public void updateStandCycles(Stand stand) throws SQLException{
        PreparedStatement query = connection.prepareStatement("update stand set cycleCount = ? where id = ?");
        query.setInt(1, stand.getCycleCount());
        query.setInt(2, stand.getId());
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

    public void addService(Service service) throws SQLException {
        PreparedStatement query = connection.prepareStatement("INSERT INTO service(location, cycleCount) values(?,?)");
//        query.setString(1, stand.getLocation());
//        query.setInt(2, stand.getCycleCount());
//        query.executeUpdate();
//        query.close();
    }



}
