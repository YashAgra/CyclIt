import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;

public class Database {
    public static final String connection_url = "jdbc:mysql://localhost:3306/cycleit";
    public static final String user = "root";
    public static final String password = "12345678";
    Connection connection = null;
    Database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connection_url, user, password);
    }


    public void addStand(Stand stand) throws SQLException {
        PreparedStatement query = connection.prepareStatement("INSERT INTO stand values(?,?,?)");
        query.setInt(1,stand.getId());
        query.setString(2, stand.getLocation());
        query.setInt(3, stand.getCycleCount());
        query.executeUpdate();
        query.close();
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
}
