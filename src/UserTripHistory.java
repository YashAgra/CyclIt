public class UserTripHistory {
    private int userID;
    private int cycleId;
    private int payID;
    private String startTime;
    private String endTime;
    private int distance;
    private int sourceStandID;
    private int destStandId;
    private String Date;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCycleId() {
        return cycleId;
    }

    public void setCycleId(int cycleId) {
        this.cycleId = cycleId;
    }

    public int getPayID() {
        return payID;
    }

    public void setPayID(int payID) {
        this.payID = payID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getSourceStandID() {
        return sourceStandID;
    }

    public void setSourceStandID(int sourceStandID) {
        this.sourceStandID = sourceStandID;
    }

    public int getDestStandId() {
        return destStandId;
    }

    public void setDestStandId(int destStandId) {
        this.destStandId = destStandId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


}
