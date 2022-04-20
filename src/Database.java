import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static final String connection_url = "jdbc:mysql://localhost:3306/cycleit";
    public static final String user = "root";
    public static final String password = "ujjwal";
    public static Connection connection = null;
    Database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connection_url, user, password);
    }


   //executeUpdate: create update, delete
    public static void addStand(Stand stand) throws SQLException {
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

    public void deleteCycle(int cid) throws SQLException {
        PreparedStatement query = connection.prepareStatement("DELETE from cycle where cycle_id=?");
        query.setInt(1,cid);
        query.executeUpdate();
        query.close();
    }



}
