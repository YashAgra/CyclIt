import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Cyclit {

    static Database db;

    static {
        try {
            db = new Database();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public static void login() throws IOException, SQLException, ClassNotFoundException {

        //enter user and pass

        System.out.println("Enter User ID: ");
        String userid = Reader.nextLine(); //user id means email id of the user (which means email id of the user)
        System.out.println("Enter Password: ");
        String pass = Reader.nextLine();

        int index=-1;
        for(int i=0;i<userid.length();i++){
            if(userid.charAt(i)=='@') index=i;
        }
        if(index==-1) System.out.println("Invalid Email id");  //TODO TRACE BACK TO LOGIN PAGE
        boolean isEmployee=false;
        String sub_email=userid.substring(index,userid.length());
        if(sub_email.equals("@cyclit.in")) {
            isEmployee=true;
        }

        if(isEmployee){
            its_a_employee(userid,pass);
        }

        its_a_user(userid,pass);
        //check the cred in database and login
    }

    private static void its_a_employee(String userid,String pass) throws SQLException, IOException, ClassNotFoundException {
        Employee emp=Employee.getfromdb(userid,pass);
        if(emp==null){
            System.out.println("--------Wrong details try again------------\n");
        }
        int emp_id=emp.getEmployeeId();
        String type=emp.getType();
        if(type=="HR"){
            is_HR(emp);
        }
        else if(type=="CycleManager"){
            is_cycleManager(emp);
        }
        else if(type=="Service"){
            is_serviceMan(emp);
        }
        else if(type=="PR"){
            is_PRman(emp);
        }
    }

    private static void is_HR(Employee emp) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("1. Add Employee \n2. Edit Employee  \n 3. Delete employee");
        int whatToDo=Reader.nextInt();
        if(whatToDo==1){
            Employee.addtodb();
        }else if(whatToDo==2){
            Employee.updatedb();
        }
        else if(whatToDo==3){
            Employee.deletefromdb();
        }
    }

    private static void is_cycleManager(Employee emp) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("1. Close Service \n2.Delete Stand \n3.Add Stand \n4.Add Cycle \n5.Delete Cycle \n 6.Get All Cycles \n 7.Get All Stands \n8.Get All Servies");
        int whatToDo=Reader.nextInt();
        switch(whatToDo){
            case 1: Service.closeTicket();
            case 2: Stand.deleteStand();
            case 3: Stand.addStand();
            case 4: Cycle.addCycle();
            case 5: Cycle.deleteCyclefromdb();
            case 6: Cycle.listAllCycle();
            case 7: Stand.listAll();
            case 8: getAllService();
        }
    }

    private static void is_PRman(Employee emp) throws IOException, SQLException {
        System.out.println("1. Get Employee Details \n2. Get User Details\n");
        int whatToDo=Reader.nextInt();
        switch(whatToDo){
            case 1: db.getEmployeeDetials_publicInfo();
            case 2: db.getUserDetails_publicInfo();
        }
    }

    private static void is_serviceMan(Employee emp) throws IOException, SQLException {
        System.out.println("1. Get all services \n2. CLose service \n -1.exit");
        int whatToDo=Reader.nextInt();
        if(whatToDo==1){
            getAllService();
        }
        else if(whatToDo==2){
            Service.closeTicket();

        }
        else{
            //nothing;
        }
    }

    private static void its_a_user(String userid,String pass) throws SQLException, IOException {
        User user =User.getfromdb(userid, pass);
        if(user!=null) {
            System.out.println("Welcome " + user.getName() + "\n");
            ArrayList<Stand> standList = db.getAllStand();
            for (int i = 0; i < standList.size(); i++) {
                Stand stand = standList.get(i);
                System.out.print("  ");
                System.out.print(stand.getId());
                System.out.print("    |");
                System.out.print(stand.getLocation());
                int z = stand.getLocation().length();
                System.out.print(" ".repeat(41 - z) + "|");
                System.out.println(stand.getCycleCount());

                //TODO HANDLE THE EXCEPTION IF USER ID IS NOT PRESENT
            }
            System.out.println("===================================================");
            System.out.println("1. Book a bike \n2. Check menu options");
            int id = Reader.nextInt();
            switch (id) {
                case 1:
                    bookCycle(user);
                case 2:
                    displayMenu(user);
                case -1:
                    break;
            }
        }
    }

    private static void displayMenu(User user) throws IOException, SQLException {
        System.out.println("Welcome to Menu \n 1. Feedback \n 2. View your Details\n 3. View Trip History\n 4. View wallet details\n 5. Update your details\n else enter -1 to leave \n");
        System.out.println();
        int displayid = Reader.nextInt();
        while (true) {
            switch (displayid) {
                case 1:
                    feedback(user);
                case 2:
                    viewUserDetails(user);
                case 3:
                    triphistory(user);
                case 4:
                    wallet(user);
                case 5:
                    updateUserDetails(user);
                case -1:
                    break;
            }
        }
    }

    private static void getAllService() throws SQLException {
        ArrayList<Service> services=Service.getAll_Active_Services();
        System.out.println("=======================================Pending Services=====================================\n");
        System.out.println("Service_id     Cycle_id        Emp_id          fid            maintenanceInformation");

        for(int i=0;i< services.size();i++){
            Service service= services.get(i);
            System.out.println(service.getService_id()+"    "+service.getCycleID()+"     "+service.getEmployeeID()+"      "+service.getFid()+"        "+service.getMaintenanceInformation());
        }
}

    private static void updateUserDetails(User user) throws IOException, SQLException {
        System.out.println("Update User Details, Please note the options here :");
        int userid = Reader.nextInt();
        System.out.println("===============================================================================================");
        System.out.println("| User ID |  | Name |  | Roll Number | | Email ID | | Address | | Contact Number | | Password |");
        System.out.println("===============================================================================================");
        User.getfromdb(userid); //print initial user id
        User.updatedb(userid); //update user ID function
        System.out.println("===============================================================================================");
        System.out.println("| User ID |  | Name |  | Roll Number | | Email ID | | Address | | Contact Number | | Password |");
        System.out.println("===============================================================================================");
        User.getfromdb(userid);
    }

    private static void wallet(User user) throws IOException, SQLException {
        int input;
        while(true){
            System.out.println("================================================================");
            System.out.println("Current Amount : " + user.getWallet());
            System.out.println("1. Add Money");
            System.out.println("2. exit");
            input = Reader.nextInt();
            if(input == 1){
                System.out.println("==============================================================");
                System.out.println("Enter Amount: ");
                int amount = Reader.nextInt();
                Payment_interface.addPayInterface(user.getUserID(),amount,true);
                System.out.println("Confirm Amount (Y/N) : ");
                String con = Reader.nextLine();
                if(con.equals("Y") || con.equals("y")){
                    Payment_interface.UpdatePayInterface_status(user.getUserID(),true);
                    //Payment_interface.deletePayInterface_byUserId(user.getUserID());
                    user.setWallet(user.getWallet()+amount);
                    User.updatewalletMoney(user);
                    user = User.getfromdb(user.getUserID());
                }
                else {
                    System.out.println("Invalid Input (Try Again)");
                    continue;
                }

            } else if (input == 2) {
                break;
            }
        }
    }

    private static void triphistory(User user) {
        System.out.println("=========================================================================================================");
        System.out.println("| cyclid |  |source_stand|  |dest_stand|  |   startTime  |  |  endTime  |  |     Date     |  |PaymentID| ");
        System.out.println("=========================================================================================================");

    }

    private static void viewUserDetails(User user) throws SQLException {
        System.out.println("===============================================================================================");
        System.out.println("| User ID |  | Name |  | Roll Number | | Email ID | | Address | | Contact Number | | Password |");
        System.out.println("===============================================================================================");
        int i = user.getUserID();
        User.getfromdb(i);
    }

    private static void feedback(User user) throws SQLException, IOException {
        Feedback.addFeedBack(user.getUserID());
    }

    private static void bookCycle(User user) throws IOException {
        int uid = user.getUserID();
        System.out.println("Enter the stand ID: ");
        int standId = Reader.nextInt();

    }

    public static void register() throws SQLException, IOException {
        System.out.println("Please enter the following details: ");
        User.addtodb();
        System.out.println("Hurray!! You are done here. Now you can login using these credentials.\n");
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Reader.init(System.in);
        while(true){
            System.out.println("Welcome to Cyclit \n 1. Login\n 2. Register\n 3. Quit\n");
            int i = Reader.nextInt();
            switch(i){
                case 1: login();
                case 2: register();
                case 3: break;
                //TODO CHECK THE BUG : REGISTER OPTION BECOMES ACTIVE AUTOMATICALLY
            }
        }
        /*
        login page ask the user to login
        2 option : login , register

        login() function
        register(){ addUser }
         */

    }

    //-------------------------Cycle--------------------------------------------------------

    private static void addCycle() throws IOException, SQLException, ClassNotFoundException {
        Cycle.addCycle();
    }

    private static void UpdateCycleStandByCid(){
       // Cycle.UpdateCycleStandId();
    }
}

