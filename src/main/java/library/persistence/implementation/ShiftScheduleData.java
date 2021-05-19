package library.persistence.implementation;

import java.util.Date;

public class ShiftScheduleData {
    public final int shiftID;
    public final int employeeID;
    public final int category;
    public final Date date;
    public final String shiftIncidents;

    public ShiftScheduleData(int shiftID, int employeeID, int category, Date date, String shiftIncidents) {
        this.shiftID = shiftID;
        this.employeeID = employeeID;
        this.category = category;
        this.date = date;
        this.shiftIncidents = shiftIncidents;
    }
}
