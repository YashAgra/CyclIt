public class Stand {
    private int id;
    private String location;
    private int cycleCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Stand() {
    }

    public int getCycleCount() {
        return cycleCount;
    }

    public void setCycleCount(int cycleCount) {
        this.cycleCount = cycleCount;
    }

    public Stand( String location, int cycleCount) {
        this.location = location;
        this.cycleCount = cycleCount;
    }
}
