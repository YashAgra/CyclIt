
import java.io.IOException;
import java.sql.*;

public class Feedback {

    private int feedback_id;
    private int user_id;
    private String feedback;

    public int getFeedbackId( ){
        return feedback_id;
    }
    public void setFeedbackID(int feedback_id){
        this.feedback_id=feedback_id;
    }

    public int getUserId( ){
        return user_id;
    }
    public void setUserID(int user_id){
        this.user_id=user_id;
    }

    public String getFeedback( ){
        return feedback;
    }
    public void setFeedback(String feedback){
        this.feedback=feedback;
    }

    public static void addFeedBack(int user_id) throws IOException, SQLException {
        Feedback feed=new Feedback();
        feed.user_id=user_id;
        System.out.println("Enter FeedBack:");
        String feedback=Reader.nextLine();
        feed.feedback=feedback;

        Cyclit.db.addFeedBack(feed);
    }


}
