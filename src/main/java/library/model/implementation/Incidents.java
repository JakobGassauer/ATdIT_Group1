package library.model.implementation;

import library.model.Edit;

public class Incidents implements Edit<Incidents> {
    private int incidentID;
    private String description;
    private int resID;
    private int shiftID;

    public int getIncidentID() {
        return this.incidentID;
    }

    public int getResID() {
        return this.resID;
    }

    public int getShiftID() {
        return this.shiftID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Incidents{" +
                "incidentID=" + incidentID +
                ", description='" + description + '\'' +
                ", resID=" + resID +
                ", shiftID=" + shiftID +
                '}';
    }

    public Incidents(int incidentID, int resID, int shiftID, String description){
        this.description=description;
        this.incidentID=incidentID;
        this.resID=resID;
        this.shiftID=shiftID;
    }

    @Override
    public void add(Incidents incident) {
        //TODO implement add logic
    }

    @Override
    public void remove(Incidents incident) {
        //TODO implement remove logic
    }

    @Override
    public Incidents get() {
        return null;
        //TODO implement get logic
    }
}
