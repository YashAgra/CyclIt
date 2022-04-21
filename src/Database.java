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
    /*===============================STAND TABLE FUNCTIONS==========================================================*/

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
