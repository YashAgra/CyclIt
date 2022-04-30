import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class Cyclit {

    static Database db;
    static {
        try {
            db = new Database("root", "123456789");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int toMins(String s) {
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        return hoursInMins + mins;
    }
    public static void login() throws IOException, SQLException, ClassNotFoundException {

        //enter user and pass

        String userid;
        boolean isEmployee;
        String pass;
        while(true) {
            System.out.println("Enter Email ID: ");
            userid = Reader.nextLine(); //user id means email id of the user (which means email id of the user)
            System.out.println("Enter Password: ");
            pass = Reader.nextLine();

            int index = -1;
            for (int i = 0; i < userid.length(); i++) {
                if (userid.charAt(i) == '@') index = i;
            }
            if (index == -1) {
                System.out.println("Invalid Email id");
                continue;
                //TODO TRACE BACK TO LOGIN PAGE // done//
            }
            isEmployee = false;
            String sub_email = userid.substring(index, userid.length());
            if (sub_email.equals("@cyclit.in")) {
                isEmployee = true;

            }

            break;

        }
        if(isEmployee){
            its_a_employee(userid,pass);
            return;
        }

        its_a_user(userid,pass);
        //check the cred in database and login
    }

    private static void its_a_employee(String userid,String pass) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("in employee\n");
        Employee emp=Employee.getfromdb(userid,pass);
        if(emp==null){
            System.out.println("--------Wrong details try again------------\n");
            return;
        }
        int emp_id=emp.getEmployeeId();
        String type=emp.getType();
        if(type.equals("HR")){
            is_HR(emp);
            db = new Database("hr_team", "hrteam");
        }
        else if(type.equals("CycleManager")){
            is_cycleManager(emp);
            db = new Database("cycle_manager", "cyclemanager");
        }
        else if(type.equals("Service")){
            is_serviceMan(emp);
            db = new Database("service_man", "serviceman");
        }
        else if(type.equals("PR")){
            is_PRman(emp);
            db = new Database("pr_team", "analysisteam");
        }
        System.out.println("returned from employee\n");
    }

    private static void is_HR(Employee emp) throws IOException, SQLException, ClassNotFoundException {
        while(true) {
            System.out.println("1. Add Employee \n2. Edit Employee  \n3. Delete employee \n4. Find Employees who are customers also \n5. Average Salary of each department by types \n6. Total asset earned and value created in cyclit \n7.Show all Employee\n8. logout");
            int whatToDo = Reader.nextInt();
            if (whatToDo == 1) {
                Employee.addtodb();
            } else if (whatToDo == 2) {
                Employee.updatedb();
            } else if (whatToDo == 3) {
                Employee.deletefromdb();
            } else if (whatToDo == 4) {
                Cyclit.db.employeeIntersectCustomer();
            } else if (whatToDo == 5) {
                Cyclit.db.averageSalaryofEmployeeTypes();
            } else if (whatToDo == 6) {
                db.totalAssetOfCyclit();
            }
            else if (whatToDo == 7) {
                db.getEmployeeDetials_publicInfo();
            }
            else{
                break;
            }
        }
    }

    private static void is_cycleManager(Employee emp) throws IOException, SQLException, ClassNotFoundException {
        while(true) {
            boolean flag=false;
            System.out.println("1. Close Service \n2.Delete Stand \n3.Add Stand \n4.Add Cycle \n5.Delete Cycle \n6.Get All Cycles \n7.Get All Stands \n8.Get All Services \n9.Add Service \n10. All Feedbacks that were taken from Service \n11. Average cycle user time of user\n12. List of Services Closed by Employee \n-1. Logout");
            int whatToDo = Reader.nextInt();
            switch (whatToDo) {
                case 1:
                    Service.closeTicket();
                    break;
                case 2:
                    Stand.deleteStand();
                    break;
                case 3:
                    Stand.addStand();
                    break;
                case 4:
                    Cycle.addCycle();
                    break;
                case 5:
                    Cycle.deleteCyclefromdb();
                    break;
                case 6:
                    Cycle.listAllCycle();
                    break;
                case 7:
                    Stand.listAll();
                    break;
                case 8:
                    getAllService();
                    break;
                case 9:
                    Service.addService();
                    break;
                case 10:
                    db.feedbackToService();
                    break;
                case 11:
                    db.averageCycleUserTime();
                    break;
                case 12:
                    db.ListOfServicesClosedbyEmployee();
                    break;
                case -1:
                    flag=true;
                    break;
            }
            if(flag) break;
        }
    }


    private static void is_PRman(Employee emp) throws IOException, SQLException {
        while(true) {
            int flag = 0;
            System.out.println("1. Get Employee Details \n2. Get User Details\n3. Complete list details \n4. Users who have spend more than certain Value \n5. Users who have used every cycle \n6. Count number of services for users which were taken by feedback \n-1 Logout \n");
            int whatToDo = Reader.nextInt();
            switch (whatToDo) {
                case 1:
                    db.getEmployeeDetials_publicInfo();
                    break;
                case 2:
                    db.getUserDetails_publicInfo();
                    break;
                case 3:
                    db.completeUserData();
                    break;
                case 4:
                    db.averageSpendGreaterThanAmount();
                    break;
                case 5:
                    db.usersWithEveryCycle();
                    break;
                case 6:
                    db.countServiceConversionFromFeebackByUser();
                    break;
                case -1:
                    flag = 1;
                    break;
            }
            if(flag==1) break;
        }
    }

    private static void is_serviceMan(Employee emp) throws IOException, SQLException {
        while(true) {

            System.out.println("1. Get all services \n2. CLose service \n-1.logout");
            int whatToDo = Reader.nextInt();
            if (whatToDo == 1) {
                getAllService();
            } else if (whatToDo == 2) {
                Service.closeTicket();
            } else {
                break;
            }
        }

    }

    private static void its_a_user(String userid,String pass) throws SQLException, IOException, ClassNotFoundException {
        User user = User.getfromdb(userid, pass);
        if(user!=null) {

            while(true) {
                System.out.println("Welcome " + user.getName() + "\n");
                System.out.println("===================================================");
                System.out.println("1. Book a bike \n 2. End Ride \n3. Check menu options \n4. Logout");
                int id ;
                try{
                    id = Reader.nextInt();
                }catch (NumberFormatException e){
                    System.out.println("Invalid Input,Try Again");
                    continue;
                }

                int flag = 0;
                switch (id) {
                    case 1:
                        bookCycle(user);
                        break;
                    case 2:
                        endRide(user);
                        break;
                    case 3:
                        displayMenu(user);
                        break;
                    case 4:
                        flag=1;
                        break;
                }
                if(flag==1) break;
            }
        }
        else{
            System.out.println("invalid EmailID or Password \n login Again !");
            login();
        }
    }

    private static void displayMenu(User user) throws IOException, SQLException {
        System.out.println();
        while (true) {
            System.out.println("Welcome to Menu \n 1. Feedback \n 2. View your Details\n 3. View Trip History\n 4. View wallet details\n else enter -1 to leave \n");
            int displayid;

            try{
                displayid = Reader.nextInt();
            }catch (NumberFormatException e){
                System.out.println("Invalid Input,Try Again");
                continue;
            }

            int flag = 0;
            switch (displayid) {
                case 1:
                    feedback(user);
                    break;
                case 2:
                    viewUserDetails(user);
                    break;
                case 3:
                    triphistory(user);
                    break;
                case 4:
                    wallet(user);
                    break;
                case -1:
                    flag = 1;
                    break;
            }
            if(flag == 1) break;
        }
    }

    private static void getAllService() throws SQLException {
        ResultSet services=Service.getAll_Active_Services();
        System.out.println("=======================================Pending Services=====================================\n");
        net.efabrika.util.DBTablePrinter.printResultSet(services);
//        System.out.println("Service_id     Cycle_id        Emp_id          fid            maintenanceInformation");
//
//        for(int i=0;i< services.size();i++){
//            Service service= services.get(i);
//            System.out.println(service.getService_id()+"    "+service.getCycleID()+"     "+service.getEmployeeID()+"      "+service.getFid()+"        "+service.getMaintenanceInformation());
//        }
}

    private static void updateUserDetails(User user) throws IOException, SQLException {
//        System.out.println("Update User Details, Please note the options here :");
        int userid = user.getUserID();


        System.out.println("===================================================================================================");
        System.out.println("current Details\n");
        user.viewUser();
        User.updatedb(userid); //update user ID function
        System.out.println("Updated Details are: ");
        User.getfromdb(userid).viewUser();
    }

    private static void wallet(User user) throws IOException, SQLException {
        int input;
        while(true){
            System.out.println("================================================================");
            System.out.println("Current Amount : " + user.getWallet());
            System.out.println("1. Add Money");
            System.out.println("2. exit");

            try{
                input = Reader.nextInt();
            }catch (NumberFormatException e){
                System.out.println("Invalid Input");
                continue;
            }


            if(input == 1){
                System.out.println("==============================================================");
                System.out.println("Enter Amount: ");
                int amount = Reader.nextInt();
                Payment_interface.addPayInterface(user.getUserID(),amount,true);
                System.out.println("Confirm Amount (Y/N) : ");
                String con = Reader.nextLine();
                if(con.equals("Y") || con.equals("y")){
                    Payment_interface.UpdatePayInterface_status(user.getUserID(),true);
                    Payment_interface.deletePayInterface_byUserId(user.getUserID());
                    user.setWallet(user.getWallet()+amount);
                    User.updatewalletMoney(user);
                    user = User.getfromdb(user.getUserID());
                }
                else if(con.equals("N") || con.equals("n")) {
                    Payment_interface.deletePayInterface_byUserId(user.getUserID());
                    System.out.println("OK, Add Money Again");
                    continue;
                }
                else{
                    Payment_interface.deletePayInterface_byUserId(user.getUserID());
                    System.out.println("Invalid Input (Try Again)");
                    continue;
                }

            } else if (input == 2) {
                break;
            }
        }
    }

    private static void triphistory(User user) {

    }

    private static void viewUserDetails(User user) throws SQLException, IOException {
        int i = user.getUserID();
//        System.out.println("1. View User details \n 2. Update user details \n Enter -1 for breaking! ");
        int flag = 0;
        while (true) {
            System.out.println("1. View User details \n 2. Update user details \n Enter -1 for breaking! ");
            int viewUserDetailMenu = Reader.nextInt();
            switch (viewUserDetailMenu) {
                case 1:
                    user.viewUser();
                    break;
                case 2:
                    updateUserDetails(user);
                    break;
                case -1:
                    flag = 1;
                    break;
            }
            if (flag == 1) break;
        }
    }

    private static void feedback(User user) throws SQLException, IOException {
        Feedback.addFeedBack(user.getUserID());
    }


    private static void bookCycle(User user) throws IOException, SQLException, ClassNotFoundException {

        if(OngoingRides.getfromdb(user.getUserID()) != null){
            System.out.println("You Already booked a cycle !");
            return;
        }

        if(user.getWallet() <= 20 ){
            System.out.println("There should be atleast 10 Rs in your wallet money");
            return;
        }

        Stand.listAll();
        int uid = user.getUserID();
        System.out.println("Enter the stand ID: ");
        int standId = Reader.nextInt();
        Cycle.listAllCycle_notinuse(standId);
        System.out.println("Select a cycle from the list below: \n");
        System.out.println("IF No cycle check another stand (to go back type -1)\n");
        int cid = Reader.nextInt();
        if(cid == -1){
            return;
        }

        Cycle.UpdateCycleInUse_toTrue(cid);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        OngoingRides ride = new OngoingRides(uid,cid, standId, sdf.format(cal.getTime()));
        db.addOngoingRides(ride);
        System.out.println("Cycle booked successfully");

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



            if(i==1){
                login();
            } else if (i==2) {
                register();
            }
            else {
                break;
            }
                //TODO CHECK THE BUG : REGISTER OPTION BECOMES ACTIVE AUTOMATICALLY
        }
        /*
        login page ask the user to login
        2 option : login , register

        login() function
        register(){ addUser }
         */
    }
    public static void endRide(User user) throws SQLException, IOException, ClassNotFoundException {
        if(OngoingRides.getfromdb(user.getUserID()) == null) {
            return;
        }

        int destStand;
        System.out.println("Choose the stand on which you want to park the bike: ");
        Stand.listAll();
        destStand = Reader.nextInt();
        OngoingRides ride = OngoingRides.getfromdb(user.getUserID());
        int payment = generatePayment(ride);
        Payment_interface.addPayInterface(user.getUserID(),payment, false);
        System.out.println("The total amount to be paid is: "+payment);
        System.out.println("Confirm Amount (Y/N) : ");

        String con;
        try{
            con = Reader.nextLine();
        }catch(NumberFormatException e){
            System.out.println("Invalid Input, try Again");
            Payment_interface.deletePayInterface_byUserId(user.getUserID());
            return;
        }

        if(con.equals("Y") || con.equals("y")){
            PreparedStatement query = Database.connection.prepareStatement("call addtriphistory(?,?);");
            query.setInt(1,destStand );
            query.setInt(2, user.getUserID());
            query.executeUpdate();
            PreparedStatement query1 = Database.connection.prepareStatement("call incrementStandCycleCount(?);");
            query1.setInt(1,destStand );
            query1.executeUpdate();
            Payment_interface.UpdatePayInterface_status(user.getUserID(),true);
            Payment_interface.deletePayInterface_byUserId(user.getUserID());

            Cycle.UpdateCycleStandId(ride.getCycleID(),destStand);

            Cycle.UpdateCycleInUse_toFalse(ride.getCycleID());

            user.setWallet(user.getWallet()-payment);
            User.updatewalletMoney(user);
            user = User.getfromdb(user.getUserID());
//            user = User.getfromdb(user.getUserID());
        }
        else {
            Payment_interface.deletePayInterface_byUserId(user.getUserID());
            System.out.println("Invalid Input (Try Again)");
        }


    }

    private static int generatePayment(OngoingRides ride) throws SQLException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        int totalTime = toMins(sdf.format(cal.getTime())) - toMins(ride.getOutTime());
        return totalTime*2;
    }

    //-------------------------Cycle--------------------------------------------------------

    private static void addCycle() throws IOException, SQLException, ClassNotFoundException {
        Cycle.addCycle();
    }

    private static void UpdateCycleStandByCid(){
       // Cycle.UpdateCycleStandId();
    }
}

