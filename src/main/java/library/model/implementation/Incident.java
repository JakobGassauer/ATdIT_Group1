package library.model.implementation;

import library.model.Edit;
import library.persistence.implementation.DatabaseService;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Model specific type Incident
 */
public class Incident implements Edit<Incident> {
    private int incidentID;
    private String description;
    private int resID;
    private int shiftID;
    private Date incidentsDate;

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

    public Date getIncidentsDate() {
        return incidentsDate;
    }

    public void setIncidentsDate(Date incidentsDate) {
        this.incidentsDate = incidentsDate;
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

    public Incident(int incidentID, int resID, int shiftID, String description, Date incidentsDate){
        this.description=description;
        this.incidentID=incidentID;
        this.resID=resID;
        this.shiftID=shiftID;
        this.incidentsDate = incidentsDate;
    }

    @Override
    public void add(Incident incident) {
        //TODO implement add logic
    }

    @Override
    public void remove(Incident incident) {
        //TODO implement remove logic
    }


    //public static Incident get(int resID) {
     //   return DatabaseService.getSingleIncident(resID);
    //}

    //public static Incident get(int resID, Date date) {
     //   return DatabaseService.getSingleIncident(resID, date);
    //}


    @Override
    public  Incident get() {
        return null;
    }


}
