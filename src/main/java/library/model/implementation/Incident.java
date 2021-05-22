package library.model.implementation;

import java.util.Date;
/**
 * Model specific type Incident which implements the interface Edit
 */
public class Incident {
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


}
