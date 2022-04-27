

import java.io.IOException;
import java.sql.*;

public class Payment_interface {

    private int payment_id;
    private int user_id;
    private int amount;
    private boolean isWalletRecharge;
    private boolean status;

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isWalletRecharge() {
        return isWalletRecharge;
    }

    public void setWalletRecharge(boolean walletRecharge) {
        isWalletRecharge = walletRecharge;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static void  addPayInterface(int user_id,int amount, boolean isWalletRecharge) throws SQLException {
        Payment_interface pay=new Payment_interface();

        pay.user_id=user_id;
        pay.amount=amount;
        pay.isWalletRecharge=isWalletRecharge;
        pay.status=false;

        Cyclit.db.addPayInterface(pay);
    }

    public static void UpdatePayInterface_isWalletRecharge(int user_id,boolean isWallet) throws SQLException {
        Cyclit.db.UpdatePayInter_wallet(user_id,isWallet);
    }

    public static void UpdatePayInterface_status(int user_id,boolean status) throws SQLException {
        Cyclit.db.UpdatePayInter_status(user_id,status);
        //TODO: use triggers
    }

    public static void deletePayInterface_byUserId(int user_id) throws SQLException {
        Cyclit.db.deletePayInterface_byUserId(user_id);
    }


}
