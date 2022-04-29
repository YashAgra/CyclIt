
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Feedback {

    private int feedback_id;
    private int user_id;
    private String feedback;

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public static void addFeedBack(int user_id) throws IOException, SQLException {
        Feedback feed=new Feedback();
        feed.user_id=user_id;
        System.out.println("Enter FeedBack:");
        String feedback=Reader.nextLine();
        feed.feedback=feedback;
        Cyclit.db.addFeedBack(feed);
        System.out.println("Thank you for your feedback! We will communicate with you and make the necessary improvements\n");
        return;
    }

    public static ResultSet getAllFeedback() throws SQLException {
        return Cyclit.db.getAllFeedback();
    }


//todo list feedback

}
