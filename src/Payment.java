//import java.io.*;
//import java.sql.*;
//import java.util.*;
//
//public class Payment {
//    private int payment_id;
//    private int user_id;
//    private int amount;
//
//    public void setPayment_id(int payment_id) {
//        this.payment_id = payment_id;
//    }
//
//    public Payment(int payment_id, int user_id, int amount) {
//        this.payment_id = payment_id;
//        this.user_id = user_id;
//        this.amount = amount;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public int getPayment_id() {
//        return payment_id;
//    }
//
//    public int getUser_id() {
//        return user_id;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public static void addFinalPayment(int payment_id, int user_id, int amount) throws SQLException {
//        Payment payTable = new Payment(payment_id, user_id, amount);
//        Cyclit.db.addFinalPayment(payTable);
//    }
//
//    public static void addFinalPayment(int payment_id, int user_id, int amount) throws SQLException {
//        Payment payTable = new Payment(payment_id, user_id, amount);
//        Cyclit.db.addFinalPayment(payTable);
//    }
//
//}
