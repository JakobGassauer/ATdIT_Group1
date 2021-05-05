package library.model.implementation;

import library.model.Edit;
import library.persistence.implementation.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class Incident implements Edit<Incident> {
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

    public Incident(int incidentID, int resID, int shiftID, String description){
        this.description=description;
        this.incidentID=incidentID;
        this.resID=resID;
        this.shiftID=shiftID;
    }

    @Override
    public void add(Incident incident) {
        //TODO implement add logic
    }

    @Override
    public void remove(Incident incident) {
        //TODO implement remove logic
    }

    @Override
    public Incident get() {
        return null;
    }

    public static Incident get(int index) {
        List<Incident> incidentsArrayList =new ArrayList<>();
        incidentsArrayList = DatabaseService.getIncidents();
        Incident incident = incidentsArrayList.get(index);
        return incident;
        //TODO implement get logic
    }
}
