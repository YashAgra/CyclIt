import javax.print.DocFlavor;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {

    //static Database db;
    private int EmployeeId;
    private String Name;
    private String Type;
    private String EmailAddress;
    private String PhoneNumber;
    private String Address;
    private String password;

    public Employee() throws SQLException, ClassNotFoundException {
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    private int Salary;

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    static void addtodb() throws IOException, SQLException, ClassNotFoundException {
        Employee employee = new Employee();

        System.out.println("Name : ");
        employee.setName(Reader.nextLine());

        System.out.println("Phone Number : ");
        employee.setPhoneNumber(Reader.nextLine());

        System.out.println("Address : ");
        employee.setAddress(Reader.nextLine());

        System.out.println("EmailID : ");
        employee.setEmailAddress(Reader.nextLine());

        System.out.println("Password: ");
        employee.setPassword(Reader.nextLine());
        System.out.println("Type : ");
        employee.setType(Reader.next());
        System.out.println("Salary: ");
        employee.setSalary(Reader.nextInt());

        Cyclit.db.addEmployee(employee);
    }

    static Employee getfromdb(int id) throws SQLException, ClassNotFoundException {
        return Cyclit.db.getEmployee(id);
    }

    public static void deletefromdb() throws SQLException, IOException {
        System.out.println("Employee you want to remove:");
        int id=Reader.nextInt();
        Cyclit.db.deleteEmployee(id);
    }

    public static void updatedb() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Enter the employee id to update: ");
        int id = Reader.nextInt();
        Employee employee = getfromdb(id);

        int i;
        while(true){
            System.out.println("1. Change Name");
            System.out.println("2. Change Address");
            System.out.println("3. Change PhoneNumber");
            System.out.println("4. exit");

            i = Reader.nextInt();
            if(i==1){
                System.out.println("Name : ");
                employee.setName(Reader.nextLine());
            }
            else if (i==2){
                System.out.println("Address :");
                employee.setAddress(Reader.nextLine());
            } else if (i==3) {
                System.out.println("PhoneNumber : ");
                employee.setPhoneNumber(Reader.nextLine());
            }
            else {
                break;
            }
        }
        Cyclit.db.updateEmployee(employee);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Employee getfromdb(String email, String pass) throws SQLException, ClassNotFoundException {
        Database db = Cyclit.db;
        PreparedStatement query = Database.connection.prepareStatement("SELECT user_id FROM employee where email = ? and password = ?");
        query.setString(1, email);
        query.setString(2, pass);
        ResultSet resultSet = query.executeQuery();
        if(resultSet == null)
            return null;
        else{
            resultSet.next();
            int id = resultSet.getInt("eid");
            Employee emp = Employee.getfromdb(id);
            return emp;
        }
    }
}
