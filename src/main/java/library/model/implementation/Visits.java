package library.model.implementation;

/**
 * Model specific type Visits which implements the interface Edit
 */
public class Visits  {
    private int visitID;
    private String description;
    private int resID;

    public Visits(int visitID, String description, int resID){
        this.visitID=visitID;
        this.description=description;
        this.resID=resID;
    }

    public int getVisitID() {
        return visitID;
    }

    public String getDescription() {
        return description;
    }

    public int getResID() {
        return resID;
    }

    public void setVisitID(int visitID) {
        this.visitID = visitID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    @Override
    public String toString() {
        return "Visits{" +
                "visitID=" + visitID +
                ", description='" + description + '\'' +
                ", resID=" + resID +
                '}';
    }
}
