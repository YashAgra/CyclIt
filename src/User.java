import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private int UserID;
    private int RollNumber;
    private String Name;
    private String Address;
    private String EmailID;
    private String Password;
    private String PhoneNumber;
    private int Year;
    private int wallet;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(int rollorfacNumber) {
        RollNumber = rollorfacNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String contactNumber) {
        PhoneNumber = contactNumber;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    static void addtodb() throws IOException, SQLException {
        User user = new User();
        System.out.println("Name : ");
        user.setName(Reader.nextLine());

        System.out.println("RollNumber : ");
        user.setRollNumber(Reader.nextInt());

        System.out.println("EmailID : ");
        user.setEmailID(Reader.nextLine());

        System.out.println("Address : ");
        user.setAddress(Reader.nextLine());

        System.out.println("Contact Number : ");
        user.setPhoneNumber(Reader.nextLine());

        System.out.println("Password : ");
        user.setPassword(Reader.nextLine());
        user.setWallet(0);
        user.setYear(user.getRollNumber()/1000);
        Cyclit.db.addUser(user);
    }

    static User getfromdb(int id) throws SQLException {
        return Cyclit.db.getUser(id);
    }

    public static void updatedb(int id) throws SQLException, IOException {
        User user = getfromdb(id);

        int i;
        while(true){
            System.out.println("1. Change Name");
            System.out.println("2. Change Address");
            System.out.println("3. Change PhoneNumber");
            System.out.println("4. Change Password");
            System.out.println("5. exit");

            i = Reader.nextInt();
            if(i==1){
                System.out.println("Name : ");
                user.setName(Reader.nextLine());
            }
            else if (i==2){
                System.out.println("Address :");
                user.setAddress(Reader.nextLine());
            } else if (i==3) {
                System.out.println("PhoneNumber : ");
                user.setPhoneNumber(Reader.nextLine());
            } else if (i==4) {
                System.out.println("NewPassword : ");
                user.setPassword(Reader.nextLine());
            } else {
                break;
            }
        }
        Cyclit.db.updateUser(user);
    }
    static User getfromdb(String email, String pass) throws SQLException {
        Database db = Cyclit.db;
        PreparedStatement query = Database.connection.prepareStatement("SELECT user_id FROM user where email = ? and password = ?");
        query.setString(1, email);
        query.setString(2, pass);
        ResultSet resultSet = query.executeQuery();
        if(resultSet == null)
            return null;
        else{
            resultSet.next();
            int id = resultSet.getInt("user_id");
            User user = User.getfromdb(id);
            return user;
        }
    }
    static void deletefromdb(int id) throws SQLException {
        Cyclit.db.deleteUser(id);
    }

    public static void updatewalletMoney(User user) throws SQLException {
        Cyclit.db.updateWalletMoney(user);
    }

    public static ArrayList<UserTripHistory> gettripHistory(int id) throws SQLException {
        return Cyclit.db.gettripHistory(id);
    }
}
