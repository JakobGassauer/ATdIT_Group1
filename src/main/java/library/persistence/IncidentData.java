package library.persistence;

import java.util.Date;

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
