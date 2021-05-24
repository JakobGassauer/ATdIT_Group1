package library.model.implementation;

import java.util.Date;
/**
 * Model specific type ShiftSchedule which implements the interface Edit
 */
public class ShiftSchedule  {
    private int shiftID;
    private int employeeID;
    private int category;
    private Date date;
    private String shiftIncidents;



    public int getShiftID() { return this.shiftID; }

    public int getEmployeeID() { return this.employeeID; }

    public int getCategory() { return this.category; }

    public String getShiftIncidents() {
        return shiftIncidents;
    }


    public Date getDate() {
        return date;
    }

    public void setShiftID(int shiftID) { this.shiftID = shiftID; }

    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }

    public void setCategory(int category) { this.category = category; }

    public ShiftSchedule(int shiftID, int employeeID, int category, Date date, String shiftIncidents) {
        this.shiftID = shiftID;
        this.employeeID = employeeID;
        this.category = category;
        this.date = date;
        this.shiftIncidents = shiftIncidents;
    }



    @Override
    public String toString() {
        return "ShiftSchedule{" +
                "shiftID=" + shiftID +
                ", employeeID=" + employeeID +
                ", category=" + category +
                '}';
    }

    public static ShiftSchedule get(Object category, Date date) {
        return null;// DatabaseService.getSingleShiftSchedule(category, date);
    }

}
