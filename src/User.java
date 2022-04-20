import java.io.IOException;
import java.sql.SQLException;

public class User {
    private int UserID;
    private int RollorfacNumber;
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

    public int getRollorfacNumber() {
        return RollorfacNumber;
    }

    public void setRollorfacNumber(int rollorfacNumber) {
        RollorfacNumber = rollorfacNumber;
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

    static void addtodb() throws IOException {
        User user = new User();
        System.out.println("Name : ");
        user.setName(Reader.nextLine());

        System.out.println("EmailID : ");
        user.setEmailID(Reader.nextLine());

        System.out.println("Address : ");
        user.setAddress(Reader.nextLine());

        System.out.println("ContactNumber : ");
        user.setPhoneNumber(Reader.nextLine());

        System.out.println("Password : ");
        user.setPassword(Reader.nextLine());


    }

    static User getfromdb(int id) throws SQLException {
        return Cyclit.db.getUser(id);
    }
}
