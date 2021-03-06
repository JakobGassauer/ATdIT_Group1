package library.persistence.implementation;

import java.util.Date;

/**
 * Database specific type IncidentData
 */
public class IncidentData {
    public final int incidentID;
    public final String description;
    public final int resID;
    public final int shiftID;
    public final Date incidentsDate;

    public IncidentData(int incidentID, int resID,int shiftID,String description,Date incidentsDate) {
        this.incidentID = incidentID;
        this.description = description;
        this.resID = resID;
        this.shiftID = shiftID;
        this.incidentsDate = incidentsDate;
    }
}
