package library.persistence.implementation;

public class VisitsData {
    public final int visitID;
    public final String description;
    public final int resID;

    public VisitsData(int visitID, String description, int resID) {
        this.visitID = visitID;
        this.description = description;
        this.resID = resID;
    }
}
